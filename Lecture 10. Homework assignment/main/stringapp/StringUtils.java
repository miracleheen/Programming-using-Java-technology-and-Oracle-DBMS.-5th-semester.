package main.stringapp;

import java.text.Normalizer;

public class StringUtils {
    private static final String VOWELS = "aeiouAEIOU" + "аеёиоуыэюяАЕЁИОУЫЭЮЯ";

    public static boolean isPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Входная строка не должна быть null");
        }

        String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
        StringBuilder sb = new StringBuilder();
        for (var c : normalized.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        String filtered = sb.toString();
        String reversed = sb.reverse().toString();
        return filtered.equals(reversed);
    }

    public static int countVowels(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Входная строка не должна быть null");
        }

        int count = 0;
        for (var c : s.toCharArray()) {
            if (VOWELS.indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static int countConsonants(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Входная строка не должна быть null");
        }

        int count = 0;
        for (var c : s.toCharArray()) {
            if (Character.isLetter(c) && VOWELS.indexOf(c) < 0) {
                count++;
            }
        }
        return count;
    }

    public static int countOccurrences(String text, String word) {
        if (text == null) {
            throw new IllegalArgumentException("Текст не должен быть null");
        }

        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Искомое слово не должно быть null или пустым");
        }

        String lowerText = text.toLowerCase();
        String lowerWord = word.toLowerCase();
        int count = 0;
        int index = 0;

        while (true) {
            index = lowerText.indexOf(lowerWord, index);
            if (index < 0) {
                break;
            }
            count++;
            index = index + 1; // для подсчета перекрывающихся вхождений
        }

        return count;
    }
}

