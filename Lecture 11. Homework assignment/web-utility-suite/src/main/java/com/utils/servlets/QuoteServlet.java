package com.utils.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuoteServlet", urlPatterns = {"/quote"})
public class QuoteServlet extends HttpServlet {
    private static final String QUOTE = "Bad programmers worry about the code. " +
            "Good programmers worry about data structures and their relationships.";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("quoteText", QUOTE);
        request.getRequestDispatcher("/WEB-INF/jsp/quote.jsp").forward(request, response);
    }
}
