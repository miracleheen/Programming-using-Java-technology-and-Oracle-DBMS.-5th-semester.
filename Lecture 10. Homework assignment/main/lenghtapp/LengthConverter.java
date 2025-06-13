package main.lenghtapp;

public class LengthConverter {
    public static double convert(double length, LengthUnit from, LengthUnit to) {
        if (from == null) {
            throw new IllegalArgumentException("Исходная единица не должна быть null");
        }

        if (to == null) {
            throw new IllegalArgumentException("Целевая единица не должна быть null");
        }

        if (length < 0) {
            throw new IllegalArgumentException("Значение длины не может быть отрицательным");
        }

        double inMeters = length * from.getFactorToMeter();
        return inMeters / to.getFactorToMeter();
    }
}