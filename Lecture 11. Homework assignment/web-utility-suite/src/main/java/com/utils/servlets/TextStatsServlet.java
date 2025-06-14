package com.utils.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@WebServlet(name = "TextStatsServlet", urlPatterns = {"/textstats"})
public class TextStatsServlet extends HttpServlet {
    private static final Set<Character> VOWELS = new LinkedHashSet<>();
    private static final Set<Character> CONSONANTS = new LinkedHashSet<>();

    static {
        char[] rusVowels = {'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я'};
        char[] rusConsonants = {
                'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м', 'н', 'п', 'р',
                'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ'
        };

        char[] engVowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        char[] engConsonants = {
                'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q',
                'r', 's', 't', 'v', 'w', 'x', 'z'
        };

        for (var c : rusVowels) {
            VOWELS.add(Character.toLowerCase(c));
            VOWELS.add(Character.toUpperCase(c));
        }
        for (var c : engVowels) {
            VOWELS.add(Character.toLowerCase(c));
            VOWELS.add(Character.toUpperCase(c));
        }
        for (var c : rusConsonants) {
            CONSONANTS.add(Character.toLowerCase(c));
            CONSONANTS.add(Character.toUpperCase(c));
        }
        for (var c : engConsonants) {
            CONSONANTS.add(Character.toLowerCase(c));
            CONSONANTS.add(Character.toUpperCase(c));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/textStatsInput.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String text = request.getParameter("text");
        if (text == null || text.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Пожалуйста, введите непустой текст");
            request.setAttribute("text", text);
            request.getRequestDispatcher("/textStatsInput.jsp").forward(request, response);
            return;
        }

        Set<Character> foundVowels = new LinkedHashSet<>();
        Set<Character> foundConsonants = new LinkedHashSet<>();
        Set<Character> foundPunctuations = new LinkedHashSet<>();

        int countVowels = 0;
        int countConsonants = 0;
        int countPunct = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                if (VOWELS.contains(c)) {
                    countVowels++;
                    foundVowels.add(c);
                } else if (CONSONANTS.contains(c)) {
                    countConsonants++;
                    foundConsonants.add(c);
                }
            } else {
                int type = Character.getType(c);
                switch (type) {
                    case Character.CONNECTOR_PUNCTUATION:
                    case Character.DASH_PUNCTUATION:
                    case Character.START_PUNCTUATION:
                    case Character.END_PUNCTUATION:
                    case Character.INITIAL_QUOTE_PUNCTUATION:
                    case Character.FINAL_QUOTE_PUNCTUATION:
                    case Character.OTHER_PUNCTUATION:
                        countPunct++;
                        foundPunctuations.add(c);
                        break;
                }
            }
        }

        request.setAttribute("countVowels", countVowels);
        request.setAttribute("foundVowels", foundVowels);
        request.setAttribute("countConsonants", countConsonants);
        request.setAttribute("foundConsonants", foundConsonants);
        request.setAttribute("countPunct", countPunct);
        request.setAttribute("foundPunctuations", foundPunctuations);

        request.setAttribute("text", text);

        request.getRequestDispatcher("/WEB-INF/jsp/textStatsResult.jsp").forward(request, response);
    }
}
