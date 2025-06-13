package main.weightapp;

public class WeightConverterDemo {
    public static void main(String[] args) {
        double w1 = 1000; // 1000 миллиграмм => в граммы
        System.out.printf("%.2f %s = %.4f %s%n", w1, WeightUnit.MILLIGRAM,
                WeightConverter.convert(w1, WeightUnit.MILLIGRAM, WeightUnit.GRAM), WeightUnit.GRAM);

        double w2 = 2; // 2 килограмма => в граммы
        System.out.printf("%.2f %s = %.2f %s%n", w2, WeightUnit.KILOGRAM,
                WeightConverter.convert(w2, WeightUnit.KILOGRAM, WeightUnit.GRAM), WeightUnit.GRAM);

        double w3 = 5000; // 5000 грамм => в килограммы
        System.out.printf("%.2f %s = %.4f %s%n", w3, WeightUnit.GRAM,
                WeightConverter.convert(w3, WeightUnit.GRAM, WeightUnit.KILOGRAM), WeightUnit.KILOGRAM);

        double w4 = 3; // 3 тонны => в килограммы
        System.out.printf("%.2f %s = %.2f %s%n", w4, WeightUnit.TONNE,
                WeightConverter.convert(w4, WeightUnit.TONNE, WeightUnit.KILOGRAM), WeightUnit.KILOGRAM);

    }
}
