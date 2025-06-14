package com.utils.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CalculatorServlet", urlPatterns = {"/calculator"})
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/calculatorInput.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String s1 = request.getParameter("num1");
        String s2 = request.getParameter("num2");
        String operation = request.getParameter("operation");

        Double num1 = null, num2 = null;
        String errorMessage = null;

        if (s1 == null || s1.trim().isEmpty() ||
                s2 == null || s2.trim().isEmpty()) {
            errorMessage = "Оба поля с числами должны быть заполнены";
        } else if (operation == null ||
                !(operation.equals("add") || operation.equals("sub") ||
                        operation.equals("mul") || operation.equals("div") ||
                        operation.equals("pow") || operation.equals("percent"))) {
            errorMessage = "Выберите операцию.";
        } else {
            try {
                num1 = Double.valueOf(s1.trim());
                num2 = Double.valueOf(s2.trim());
            } catch (NumberFormatException e) {
                errorMessage = "Поля должны содержать корректные числовые значения";
            }
        }

        Double result = null;
        if (errorMessage == null) {
            switch (operation) {
                case "add":
                    result = num1 + num2;
                    break;
                case "sub":
                    result = num1 - num2;
                    break;
                case "mul":
                    result = num1 * num2;
                    break;
                case "div":
                    if (num2 == 0.0) {
                        errorMessage = "Делить на ноль низя";
                    } else {
                        result = num1 / num2;
                    }
                    break;
                case "pow":
                    try {
                        result = Math.pow(num1, num2);
                        if (result.isNaN() || result.isInfinite()) {
                            // Например,0^отрицательное или слишком большая степень
                            errorMessage = "Результат возведения в степень недопустим или бесконечен";
                        }
                    } catch (Exception e) {
                        errorMessage = "Ошибка при возведении в степень: " + e.getMessage();
                    }
                    break;
                case "percent":
                    result = num1 * num2 / 100.0;
                    break;
                default:
                    errorMessage = "Неизвестная операция";
                    break;
            }
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("num1", s1);
            request.setAttribute("num2", s2);
            request.setAttribute("selectedOp", operation);
            request.getRequestDispatcher("/calculatorInput.jsp").forward(request, response);
            return;
        }

        request.setAttribute("num1", num1);
        request.setAttribute("num2", num2);
        request.setAttribute("operation", operation);
        request.setAttribute("result", result);

        request.getRequestDispatcher("/WEB-INF/jsp/calculatorResult.jsp").forward(request, response);
    }
}
