import fractionmodel.*;
import utils.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

@FunctionalInterface
interface QuadFunction<T extends Comparable<T>> {
    T apply(T a, T b, T c, T d);
}

public class WorkingWithLambdas {
    //1
    private static void checkLeapYear(int year) {
        Predicate<Integer> isLeapYearCheck = (y) -> (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
        System.out.println(year + " является високосным? " + isLeapYearCheck.test(year));
    }

    private static void calculateDaysBetween(LocalDate start, LocalDate end) {
        BiFunction<LocalDate, LocalDate, Long> daysBetween = ChronoUnit.DAYS::between;
        System.out.println("Количество дней между " + start + " и " + end + " => " + daysBetween.apply(start, end));
    }

    private static void calculateFullWeeks(LocalDate start, LocalDate end) {
        BiFunction<LocalDate, LocalDate, Long> fullWeeksBetween = ChronoUnit.WEEKS::between;
        System.out.println("Количество полных недель между " + start + " и " + end +
                " => " + fullWeeksBetween.apply(start, end));
    }

    private static void printDayOfWeek(LocalDate date) {
        Function<LocalDate, DayOfWeek> dayOfWeekFunction = LocalDate::getDayOfWeek;
        System.out.println("День недели для " + date + " => " + dayOfWeekFunction.apply(date));
    }

    //3
    public static <T extends Comparable<T>> T getMaxOfFour(T a, T b, T c, T d) {
        QuadFunction<T> maxFunction = (x, y, z, w) -> {
            T max = x;
            if (y.compareTo(max) > 0) max = y;
            if (z.compareTo(max) > 0) max = z;
            if (w.compareTo(max) > 0) max = w;
            return max;
        };
        return maxFunction.apply(a, b, c, d);
    }

    public static <T extends Comparable<T>> T getMinOfFour(T a, T b, T c, T d) {
        QuadFunction<T> minFunction = (x, y, z, w) -> {
            T min = x;
            if (y.compareTo(min) < 0) min = y;
            if (z.compareTo(min) < 0) min = z;
            if (w.compareTo(min) < 0) min = w;
            return min;
        };
        return minFunction.apply(a, b, c, d);
    }

    public static void main(String[] args) {
        //1
        /*checkLeapYear(2024);
        calculateDaysBetween(LocalDate.of(1970, 1, 1),
                LocalDate.of(2025, 12, 31));
        calculateFullWeeks(LocalDate.of(1999, 4, 21),
                LocalDate.of(2025, 4, 15));
        printDayOfWeek(LocalDate.of(1969, 7, 20));*/

        //2
        /*Fraction firstFraction = new Fraction(3, 7);
        Fraction secondFraction = new Fraction(2, 3);

        Fraction sumResult = FractionUtilsOperations.sum(firstFraction, secondFraction);
        Fraction differenceResult = FractionUtilsOperations.difference(firstFraction, secondFraction);
        Fraction productResult = FractionUtilsOperations.product(firstFraction, secondFraction);
        Fraction divisionResult = FractionUtilsOperations.division(firstFraction, secondFraction);

        System.out.println("Сумма: " + sumResult);
        System.out.println("Разность: " + differenceResult);
        System.out.println("Произведение: " + productResult);
        System.out.println("Деление: " + divisionResult);*/

        //3
        /*int a = 22, b = 8, c = 14, d = 3;
        System.out.println("Максимум: " + getMaxOfFour(a, b, c, d));
        System.out.println("Минимум: " + getMinOfFour(a, b, c, d));*/

        //4
        /*int[] array = {1, 10, 3, -4, 5, 6, -7, 8, 9, 10, 4};

        int targetCheck = 10;
        int a = 3, b = 8;

        System.out.println("Сумма элементов, равных " + targetCheck + ": " + ArrayUtils.sumEquals(array, targetCheck));
        System.out.println("Сумма элементов, не в диапазоне от " + a + " до " + b + ": " + ArrayUtils.sumNotInRange(array, a, b));
        System.out.println("Сумма положительных элементов: " + ArrayUtils.sumPositive(array));
        System.out.println("Сумма отрицательных элементов: " + ArrayUtils.sumNegative(array));*/

    }
}
