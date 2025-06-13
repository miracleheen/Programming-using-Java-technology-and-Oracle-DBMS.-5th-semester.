import math.*;

import java.util.Random;


public class Main {
    public static void main(String[] args) {
        //1 task -> first
        /*Integer[] intArr = new Integer[10];
        Array<Integer> arrTest = new Array<>(intArr);

        String[] strArr = {"Computer", "Qwerty", "Java", "Coffee", "Porsche"};
        Array<String> checkStr = new Array<>(strArr);

        arrTest.fillRandom(() -> new Random().nextInt(101));
        System.out.print("Исходный массив (int): ");
        arrTest.display();

        System.out.print("\nИсходный массив (string): ");
        checkStr.display();

        arrTest.sortAscending();
        System.out.print("\nСортировка по возрастанию: ");
        arrTest.display();

        arrTest.sortDescending();
        System.out.print("\nСортировка по убыванию: ");
        arrTest.display();

        arrTest.replace(5, 123);
        System.out.print("\nПосле замены элемента: ");
        arrTest.display();

        checkStr.replace(1, "Stroustrup");
        System.out.print("\nМассив строк после замены: ");
        checkStr.display();

        Integer max3 = arrTest.maxOfThree(23, 81, 36);
        System.out.println("\nMax из трёх: " + max3);

        Integer min5 = arrTest.minOfFive(50, 20, 30, 40, 10);
        System.out.println("\nMin из пяти: " + min5);

        double avg = arrTest.average();
        System.out.println("\nСреднее значение: " + avg);

        arrTest.sortAscending();
        int index = arrTest.binarySearch(123);
        System.out.println("\nИндекс искомого элемента 123: " + index);

        Integer[] anotherArray = {15, 28, 42, 10, 63, 58, 2, -10, 74};
        Integer maxExternal = arrTest.maxOfArray(anotherArray);
        Integer minExternal = arrTest.minOfArray(anotherArray);
        System.out.println("\nМаксимум во внешнем массиве: " + maxExternal);
        System.out.println("Минимум во внешнем массиве: " + minExternal);*/

        //2 task
        /*Matrix<Double> m1 = new Matrix<>(3, 3);
        Matrix<Double> m2 = new Matrix<>(3, 3);

        m1.fillRandom(0, 10);
        m2.fillRandom(0, 10);

        System.out.println("Матрица m1:");
        m1.display();
        System.out.println("Матрица m2:");
        m2.display();

        System.out.println("\nСумма матриц:");
        Matrix<Double> sum = m1.add(m2);
        sum.display();

        System.out.println("\nРазность матриц:");
        Matrix<Double> diff = m1.subtract(m2);
        diff.display();

        System.out.println("\nПроизведение матриц:");
        Matrix<Double> prod = m1.multiply(m2);
        prod.display();

        Matrix<Double> div;
        try {
            div = m1.divide(m2);
            if (div != null) {
                System.out.println("\nПоэлементное деление двух матриц");
                div.display();
            }
        } catch (ArithmeticException e) {
            System.out.println("Ошибка при делении: " + e.getMessage());
        }

        System.out.printf("\nMax элемент m1: %.2f%n", m1.getMax());
        System.out.printf("Min элемент m1: %.2f%n", m1.getMin());
        System.out.printf("Avg элементов m1: %.2f%n", m1.getAverage());

        System.out.printf("\nMax элемент m2: %.2f%n", m2.getMax());
        System.out.printf("Min элемент m2: %.2f%n", m2.getMin());
        System.out.printf("Avg элементов m2: %.2f%n", m2.getAverage());*/
    }
}