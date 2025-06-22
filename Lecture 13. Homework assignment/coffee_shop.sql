-- использовал https://onecompiler.com/postgresql

CREATE
DATABASE coffee_shop
    WITH OWNER = postgres
         ENCODING = 'UTF8'
         LC_COLLATE = 'ru_RU.UTF-8'
         LC_CTYPE = 'ru_RU.UTF-8'
         TEMPLATE = template0;

\connect
coffee_shop;

-- Создание типа для позиций персонала
DO
$$
BEGIN
   IF
NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'staff_position') THEN
CREATE TYPE public.staff_position AS ENUM ('barista','waiter','confectioner');
END IF;
END$$;

CREATE TABLE public.drinks
(
    drink_id SERIAL PRIMARY KEY,
    name_ru  VARCHAR(100)   NOT NULL,
    name_en  VARCHAR(100)   NOT NULL,
    price    NUMERIC(10, 2) NOT NULL CHECK (price >= 0)
);

CREATE TABLE public.desserts
(
    dessert_id SERIAL PRIMARY KEY,
    name_ru    VARCHAR(100)   NOT NULL,
    name_en    VARCHAR(100)   NOT NULL,
    price      NUMERIC(10, 2) NOT NULL CHECK (price >= 0)
);

CREATE TABLE public.staff
(
    staff_id    SERIAL PRIMARY KEY,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    position public.staff_position NOT NULL,
    hired_date  DATE        NOT NULL     DEFAULT CURRENT_DATE,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE public.staff_phones
(
    phone_id   SERIAL PRIMARY KEY,
    staff_id   INT         NOT NULL REFERENCES public.staff (staff_id) ON DELETE CASCADE,
    phone      VARCHAR(20) NOT NULL,
    note       VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE public.staff_emails
(
    email_id   SERIAL PRIMARY KEY,
    staff_id   INT          NOT NULL REFERENCES public.staff (staff_id) ON DELETE CASCADE,
    email      VARCHAR(100) NOT NULL,
    note       VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    CONSTRAINT valid_email CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$'
)
    );

CREATE TABLE public.customers
(
    customer_id SERIAL PRIMARY KEY,
    first_name  VARCHAR(50)  NOT NULL,
    last_name   VARCHAR(50)  NOT NULL,
    middle_name VARCHAR(50),
    birth_date  DATE         NOT NULL,
    phone       VARCHAR(20)  NOT NULL,
    email       VARCHAR(100) NOT NULL,
    discount    NUMERIC(5, 2)            DEFAULT 0 CHECK (discount >= 0 AND discount <= 100),
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT now(),
    CONSTRAINT valid_cust_email CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$'
)
    );

CREATE TABLE public.orders
(
    order_id       SERIAL PRIMARY KEY,
    customer_id    INT REFERENCES public.customers (customer_id),
    waiter_id      INT            REFERENCES public.staff (staff_id) ON DELETE SET NULL,
    order_datetime TIMESTAMP WITH TIME ZONE DEFAULT now(),
    total_amount   NUMERIC(10, 2) NOT NULL CHECK (total_amount >= 0)
);

CREATE TABLE public.schedule
(
    schedule_id SERIAL PRIMARY KEY,
    staff_id    INT  NOT NULL REFERENCES public.staff (staff_id) ON DELETE CASCADE,
    work_date   DATE NOT NULL,
    start_time  TIME NOT NULL,
    end_time    TIME NOT NULL,
    CHECK (end_time > start_time)
);

CREATE TABLE public.order_items
(
    item_id      SERIAL PRIMARY KEY,
    order_id     INT            NOT NULL REFERENCES public.orders (order_id) ON DELETE CASCADE,
    product_type VARCHAR(10)    NOT NULL CHECK (product_type IN ('drink', 'dessert')),
    product_id   INT            NOT NULL,
    quantity     INT            NOT NULL CHECK (quantity > 0),
    price        NUMERIC(10, 2) NOT NULL CHECK (price >= 0)
);

-- Триггер проверки наличия продукта в ассортименте
CREATE
OR REPLACE FUNCTION public.check_order_item() RETURNS TRIGGER AS $$
BEGIN
    IF
NEW.product_type = 'drink' THEN
        PERFORM 1 FROM public.drinks WHERE drink_id = NEW.product_id;
        IF
NOT FOUND THEN RAISE EXCEPTION 'Drink with id % doesn"t exist', NEW.product_id;
END IF;
ELSE
        PERFORM 1 FROM public.desserts WHERE dessert_id = NEW.product_id;
        IF
NOT FOUND THEN RAISE EXCEPTION 'Dessert with id % does not exist', NEW.product_id;
END IF;
END IF;
RETURN NEW;
END; $$
LANGUAGE plpgsql;



CREATE TRIGGER trg_check_order_item
    BEFORE INSERT OR
UPDATE ON public.order_items
    FOR EACH ROW EXECUTE PROCEDURE public.check_order_item();

-- CRUD функции
CREATE
OR REPLACE FUNCTION public.add_row(table_name TEXT, cols TEXT[], vals TEXT[]) RETURNS VOID LANGUAGE plpgsql AS $$
DECLARE
sql TEXT;
BEGIN
sql := format(
      'INSERT INTO %I (%s) VALUES (%s)',
      table_name,
      array_to_string(cols, ','),
      array_to_string(vals, ',')
    );
EXECUTE sql;
END; $$;

CREATE
OR REPLACE FUNCTION public.delete_row(table_name TEXT, pk_name TEXT, pk_value TEXT) RETURNS VOID LANGUAGE plpgsql AS $$
BEGIN
EXECUTE format('DELETE FROM %I WHERE %I = %L', table_name, pk_name, pk_value);
END; $$;

CREATE
OR REPLACE FUNCTION public.update_row(table_name TEXT, pk_name TEXT, pk_value TEXT,
                                            col_name TEXT, col_value TEXT) RETURNS VOID LANGUAGE plpgsql AS $$
BEGIN
EXECUTE format(
        'UPDATE %I SET %I = %L WHERE %I = %L',
        table_name, col_name, col_value, pk_name, pk_value
        );
END; $$;

-- Начальные данные (условимся на 3-ех)
INSERT INTO public.drinks (name_ru, name_en, price)
VALUES ('Эспрессо', 'Espresso', 2.50),
       ('Американо', 'Americano', 2.00),
       ('Капучино', 'Cappuccino', 3.00);

INSERT INTO public.desserts (name_ru, name_en, price)
VALUES ('Чизкейк', 'Cheesecake', 4.50),
       ('Маффин', 'Muffin', 2.50),
       ('Эклер', 'Eclair', 3.00);

INSERT INTO public.staff (first_name, last_name, middle_name, position, hired_date)
VALUES ('Олег', 'Тараканов', 'Викторович', 'barista', '2025-04-10'),
       ('Мария', 'Иванова', 'Сергеевна', 'waiter', '2024-01-12'),
       ('Дмитрий', 'Петров', 'Алексеевич', 'confectioner', '2022-06-11');

INSERT INTO public.staff_phones (staff_id, phone, note)
VALUES (1, '+7-918-773-0281', 'рабочий'),
       (2, '+7-906-555-1234', 'рабочий'),
       (3, '+7-916-222-3344', 'рабочий');

INSERT INTO public.staff_emails (staff_id, email, note)
VALUES (1, 'konstantin90@gmail.com', 'рабочий'),
       (2, 'm.ivanova@example.com', 'рабочий'),
       (3, 'd.petrov@example.com', 'рабочий');

INSERT INTO public.customers (first_name, last_name, middle_name, birth_date, phone, email, discount)
VALUES ('Павел', 'Денисенко', 'Иванович', '1988-03-22', '+7-913-987-7635', 'jeremy123@gmail.com', 15.00),
       ('Анна', 'Сидорова', 'Петровна', '1992-07-14', '+7-905-123-4567', 'anna.sidorova@mail.ru', 10.00),
       ('Елена', 'Кузнецова', 'Сергеевна', '1985-11-30', '+7-917-555-6677', 'elena.kuznetsova@mail.ru', 5.00);


-- Добавление новой позиции в ассортимент кафе
INSERT INTO public.drinks (name_ru, name_en, price)
VALUES ('Латте', 'Latte', 3.80);
INSERT INTO public.desserts(name_ru, name_en, price)
VALUES ('Панна-котта', 'Panna Cotta', 4.20);

-- Добавление информации о новом кондитере
INSERT INTO public.staff (first_name, last_name, middle_name, position, hired_date)
VALUES ('Алексей', 'Смирнов', 'Иванович', 'confectioner', '2025-06-15');
INSERT INTO public.staff_phones (staff_id, phone, note)
VALUES (currval(pg_get_serial_sequence('public.staff', 'staff_id')), '+7-901-234-56-78', 'рабочий');
INSERT INTO public.staff_emails (staff_id, email, note)
VALUES (currval(pg_get_serial_sequence('public.staff', 'staff_id')), 'a.smirnov@example.com', 'рабочий');

-- добавление информации о новом клиенте
INSERT INTO public.customers (first_name, last_name, middle_name, birth_date, phone, email, discount)
VALUES ('Игорь', 'Новиков', 'Петрович', '1995-02-20', '+7-912-345-67-89', 'igor.novikov@mail.ru', 0.00);

-- Изменить цену на определенный вид кофе
UPDATE public.drinks
SET price = 2.80
WHERE drink_id = 1;

-- Изменить контактный почтовый адрес кондитеру
UPDATE public.staff_emails
SET email = 'alex.smirnov@newmail.ru'
WHERE staff_id = (SELECT MAX(staff_id) FROM public.staff);

-- Изменить контактный телефон бариста
UPDATE public.staff_phones
SET phone = '+7-918-000-11-22'
WHERE staff_id = 1;

-- Изменить процент скидки на конкретного клиента
UPDATE public.customers
SET discount = 20.00
WHERE customer_id = 1;



-- Добавление нового заказа кофе
WITH new_order AS (
INSERT
INTO public.orders (customer_id, waiter_id, order_datetime, total_amount)
VALUES (
    1, (SELECT staff_id FROM public.staff WHERE position ='waiter' LIMIT 1), now(), 5.50
    )
    RETURNING order_id
    )
INSERT
INTO public.order_items (order_id, product_type, product_id, quantity, price)
SELECT order_id, 'drink', 1, 2, 2.50
FROM new_order;

-- Добавление нового заказа десерта
WITH new_order AS (
INSERT
INTO public.orders (customer_id, waiter_id, order_datetime, total_amount)
VALUES (
    2, (SELECT staff_id FROM public.staff WHERE position ='waiter' LIMIT 1), now(), 4.00
    )
    RETURNING order_id
    )
INSERT
INTO public.order_items (order_id, product_type, product_id, quantity, price)
SELECT order_id, 'dessert', 2, 1, 2.50
FROM new_order;

-- Добавление расписания на понедельник
INSERT INTO public.schedule (staff_id, work_date, start_time, end_time)
VALUES (1, '2025-06-16', '09:00', '17:00');

-- Добавление нового вида кофе
INSERT INTO public.drinks (name_ru, name_en, price)
VALUES ('Мокко', 'Mocha', 4.20);

-- Изменить расписание работы в ближайший вторник
UPDATE public.schedule
SET start_time = '10:00',
    end_time   = '18:00'
WHERE staff_id = 1
  AND work_date = '2025-06-17';

--  Изменить название уже существующего вида кофе
UPDATE public.drinks
SET name_ru = 'Эспрессо макиато',
    name_en = 'Espresso Macchiato'
WHERE drink_id = 3;

-- Изменить информацию в существующем заказе
UPDATE public.orders
SET total_amount = 6.00
WHERE order_id = (SELECT MAX(order_id) FROM public.orders);
UPDATE public.order_items
SET quantity = 3,
    price    = 2.00
WHERE order_id = (SELECT MAX(order_id) FROM public.orders)
  AND
    item_id = (SELECT MIN(item_id) FROM public.order_items WHERE order_id = (SELECT MAX(order_id) FROM public.orders));

-- Изменить название уже существующего десерта
UPDATE public.desserts
SET name_ru = 'Шоколадный фондан',
    name_en = 'Chocolate Fondant'
WHERE dessert_id = 3;

DELETE
FROM public.desserts
WHERE dessert_id = 2;

DELETE
FROM public.staff
WHERE staff_id = 2
  AND position = 'waiter';

DELETE
FROM public.staff
WHERE staff_id = 1
  AND position = 'barista';

DELETE
FROM public.customers
WHERE customer_id = 3;

DELETE
FROM public.orders
WHERE order_id = (SELECT MAX(order_id) FROM public.orders);

DELETE
FROM public.order_items
WHERE product_type = 'dessert'
  AND product_id = 3;

DELETE
FROM public.schedule
WHERE work_date = '2025-06-16';

DELETE
FROM public.schedule
WHERE work_date BETWEEN '2025-06-16' AND '2025-06-20';

SELECT drink_id, name_ru, name_en, price
FROM public.drinks
ORDER BY drink_id;

SELECT dessert_id, name_ru, name_en, price
FROM public.desserts
ORDER BY dessert_id;

SELECT staff_id, first_name, last_name, middle_name, hired_date
FROM public.staff
WHERE position = 'barista'
ORDER BY staff_id;

SELECT staff_id, first_name, last_name, middle_name, hired_date
FROM public.staff
WHERE position = 'waiter'
ORDER BY staff_id;

SELECT o.order_id, o.customer_id, o.waiter_id, o.order_datetime, oi.quantity, oi.price
FROM public.orders o
         JOIN public.order_items oi ON o.order_id = oi.order_id
WHERE oi.product_type = 'dessert'
  AND oi.product_id = 1
ORDER BY o.order_datetime;

-- показать расписание работы на конкретный день
SELECT s.schedule_id, s.staff_id, st.first_name, st.last_name, s.start_time, s.end_time
FROM public.schedule s
         JOIN public.staff st ON s.staff_id = st.staff_id
WHERE s.work_date = '2025-06-17'
ORDER BY s.start_time;

-- показать все заказы конкретного официанта
SELECT order_id, customer_id, order_datetime, total_amount
FROM public.orders
WHERE waiter_id = (SELECT staff_id FROM public.staff WHERE position = 'waiter' LIMIT 1)
ORDER BY order_datetime;

-- показать все заказы конкретного клиента
SELECT order_id, waiter_id, order_datetime, total_amount
FROM public.orders
WHERE customer_id = 1
ORDER BY order_datetime;
