package com.pizza.model;

public enum Topping {
    OLIVES("Оливки", 50),
    CAPERS("Каперсы", 60),
    EXTRA_CHEESE("Дополнительный сыр", 70),
    MUSHROOMS("Грибы", 50),
    PEPPERONI("Пепперони", 80),
    HAM("Ветчина", 80),
    BACON("Бекон", 90),
    ONIONS("Лук", 30),
    PEPPERS("Перец", 40),
    PINEAPPLE("Ананас", 60);

    private final String displayName;
    private final int price; // в руб

    Topping(String displayName, int price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getPrice() {
        return price;
    }
}
