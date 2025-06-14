package com.pizza.servlet;

import com.pizza.model.PizzaType;
import com.pizza.model.Topping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "OrderFormServlet", urlPatterns = {"/order"})
public class OrderFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("pizzaTypes", PizzaType.values());
        request.setAttribute("allToppings", Topping.values());
        request.getRequestDispatcher("/orderForm.jsp").forward(request, response);
    }
}
