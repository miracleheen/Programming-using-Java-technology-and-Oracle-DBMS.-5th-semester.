package com.utils.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatsServlet", urlPatterns = {"/stats"})
public class StatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/statsInput.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String s1 = request.getParameter("num1");
        String s2 = request.getParameter("num2");
        String s3 = request.getParameter("num3");
        String operation = request.getParameter("operation");

        Double num1 = null, num2 = null, num3 = null;
        String errorMessage = null;

        if (s1 == null || s1.trim().isEmpty() ||
                s2 == null || s2.trim().isEmpty() ||
                s3 == null || s3.trim().isEmpty()) {
            errorMessage = "Все три поля должны быть заполнены";
        } else if (operation == null || (!operation.equals("max") && !operation.equals("min") && !operation.equals("avg"))) {
            errorMessage = "Нужно выбрать операцию: max, min или avg";
        } else {
            try {
                num1 = Double.valueOf(s1.trim());
                num2 = Double.valueOf(s2.trim());
                num3 = Double.valueOf(s3.trim());
            } catch (NumberFormatException e) {
                errorMessage = "Поля должны содержать корректные числовые значения";
            }
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("num1", s1);
            request.setAttribute("num2", s2);
            request.setAttribute("num3", s3);
            request.setAttribute("selectedOp", operation);
            request.getRequestDispatcher("/statsInput.jsp").forward(request, response);
            return;
        }

        double result;
        switch (operation) {
            case "max":
                result = num1;
                if (num2 > result) result = num2;
                if (num3 > result) result = num3;
                break;
            case "min":
                result = num1;
                if (num2 < result) result = num2;
                if (num3 < result) result = num3;
                break;
            case "avg":
                result = (num1 + num2 + num3) / 3.0;
                break;
            default:
                result = 0; //на всякий случай
                break;
        }

        request.setAttribute("num1", num1);
        request.setAttribute("num2", num2);
        request.setAttribute("num3", num3);
        request.setAttribute("operation", operation);
        request.setAttribute("resultValue", result);

        request.getRequestDispatcher("/WEB-INF/jsp/statsResult.jsp").forward(request, response);
    }
}
