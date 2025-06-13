import java.util.List;
import java.util.Random;

public class NumberStats {
    private static boolean isPalindrome(int number) {
        String s = String.valueOf(Math.abs(number));
        String rev = new StringBuilder(s).reverse().toString();
        return s.equals(rev);
    }

    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> numbers = random
                .ints(1000, -1000, 1000)
                .boxed()
                .toList();

        long positiveCount = numbers.stream()
                .filter(n -> n > 0)
                .count();

        long negativeCount = numbers.stream()
                .filter(n -> n < 0)
                .count();

        long twoDigitCount = numbers.stream()
                .filter(n -> {
                    int abs = Math.abs(n);
                    return abs >= 10 && abs <= 99;
                })
                .count();

        long palindromeCount = numbers.stream()
                .filter(NumberStats::isPalindrome)
                .count();

        System.out.println("Количество положительных: " + positiveCount);
        System.out.println("Количество отрицательных: " + negativeCount);
        System.out.println("Количество двузначных: " + twoDigitCount);
        System.out.println("Количество зеркальных: " + palindromeCount);
    }
}

