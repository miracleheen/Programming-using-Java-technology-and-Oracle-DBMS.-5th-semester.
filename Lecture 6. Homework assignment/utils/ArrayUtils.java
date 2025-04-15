package utils;

import java.util.function.Predicate;

public class ArrayUtils {

    public static int sumIf(int[] array, Predicate<Integer> condition) {
        int sum = 0;
        for (int num : array) {
            if (condition.test(num)) {
                sum += num;
            }
        }
        return sum;
    }

    public static int sumEquals(int[] array, int value) {
        return sumIf(array, n -> n == value);
    }

    public static int sumNotInRange(int[] array, int a, int b) {
        return sumIf(array, n -> n < a || n > b);
    }

    public static int sumPositive(int[] array) {
        return sumIf(array, n -> n > 0);
    }

    public static int sumNegative(int[] array) {
        return sumIf(array, n -> n < 0);
    }
}
