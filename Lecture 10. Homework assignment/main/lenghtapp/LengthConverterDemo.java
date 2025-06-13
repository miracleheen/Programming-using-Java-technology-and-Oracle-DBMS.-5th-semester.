package main.lenghtapp;

public class LengthConverterDemo {
    public static void main(String[] args) {
        double v1 = 1000; // 1000 миллиметров => в метры
        System.out.printf("%.2f %s = %.4f %s%n", v1,
                LengthUnit.MILLIMETER, LengthConverter.convert(v1, LengthUnit.MILLIMETER,
                        LengthUnit.METER), LengthUnit.METER);

        double v2 = 2; // 2 километра => в метры
        System.out.printf("%.2f %s = %.2f %s%n", v2,
                LengthUnit.KILOMETER, LengthConverter.convert(v2, LengthUnit.KILOMETER,
                        LengthUnit.METER), LengthUnit.METER);

        double v3 = 123; // 123 сантиметра => в дециметры
        System.out.printf("%.2f %s = %.4f %s%n", v3,
                LengthUnit.CENTIMETER, LengthConverter.convert(v3, LengthUnit.CENTIMETER,
                        LengthUnit.DECIMETER), LengthUnit.DECIMETER);

    }
}
