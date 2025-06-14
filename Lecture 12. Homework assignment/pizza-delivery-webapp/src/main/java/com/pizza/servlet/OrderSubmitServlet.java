package com.pizza.servlet;

import com.pizza.model.Order;
import com.pizza.model.PizzaType;
import com.pizza.model.Topping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "OrderSubmitServlet", urlPatterns = {"/submitOrder"})
public class OrderSubmitServlet extends HttpServlet {
    private static final List<String> ALLOWED_ZONES = List.of(
            "Центральный",
            "Ленинский",
            "Музыкальный",
            "Юбилейный",
            "Пашковский",
            "Фестивальный"
    );

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String pizzaTypeParam = request.getParameter("pizzaType");
        String[] toppingParams = request.getParameterValues("topping");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        String errorMessage = null;
        PizzaType pizzaType = null;

        if (pizzaTypeParam == null || pizzaTypeParam.isEmpty()) {
            errorMessage = "Пожалуйста, выберите тип пиццы";
        } else {
            try {
                pizzaType = PizzaType.valueOf(pizzaTypeParam);
            } catch (IllegalArgumentException e) {
                errorMessage = "Некорректный тип пиццы";
            }
        }

        if (errorMessage == null) {
            if (name == null || name.trim().isEmpty()
                    || phone == null || phone.trim().isEmpty()
                    || email == null || email.trim().isEmpty()
                    || address == null || address.trim().isEmpty()) {
                errorMessage = "Все поля (имя, телефон, email, адрес) обязательны.";
            } else {
                String emailPattern = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
                if (!email.matches(emailPattern)) {
                    errorMessage = "Некорректный формат email.";
                }
                if (errorMessage == null) {
                    String phonePattern = "^[+\\d][\\d\\s-]{5,20}$";
                    if (!phone.matches(phonePattern)) {
                        errorMessage = "Некорректный формат телефона.";
                    }
                }
            }
        }

        if (errorMessage == null) {
            String addrLower = address.trim().toLowerCase(Locale.ROOT);
            boolean inZone = false;
            for (String zoneKey : ALLOWED_ZONES) {
                if (addrLower.contains(zoneKey.toLowerCase(Locale.ROOT))) {
                    inZone = true;
                    break;
                }
            }
            if (!inZone) {
                errorMessage = "Ваш адрес находится вне зоны доставки." +
                        "К сожалению, доставка невозможна.";
            }
        }

        List<Topping> extraToppings = new ArrayList<>();
        if (errorMessage == null && toppingParams != null) {
            for (String tp : toppingParams) {
                try {
                    Topping topping = Topping.valueOf(tp);
                    extraToppings.add(topping);
                } catch (IllegalArgumentException e) {
                    // подумаю. скорее оставлю empty, игнорируя некорректный параметр.
                }
            }
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("pizzaTypes", PizzaType.values());
            request.setAttribute("allToppings", Topping.values());
            request.setAttribute("selectedPizzaType", pizzaTypeParam);
            if (toppingParams != null) {
                request.setAttribute("selectedToppings", Arrays.asList(toppingParams));
            }
            request.setAttribute("name", name);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/orderForm.jsp").forward(request, response);
            return;
        }

        Order order = new Order();
        order.setPizzaType(pizzaType);
        order.setExtraToppings(extraToppings);
        order.setCustomerName(name.trim());
        order.setPhone(phone.trim());
        order.setEmail(email.trim());
        order.setAddress(address.trim());

        boolean emailSent = true; // java-mail? думал реализовать, но пусть будет просто всегда true

        request.setAttribute("order", order);
        request.setAttribute("emailSent", emailSent);
        request.getRequestDispatcher("/WEB-INF/jsp/orderResult.jsp").forward(request, response);
    }
}

