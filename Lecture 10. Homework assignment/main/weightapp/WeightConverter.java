package main.weightapp;

public class WeightConverter {
    public static double convert(double weight, WeightUnit from, WeightUnit to) {
        if (from == null) {
            throw new IllegalArgumentException("Исходная единица не должна быть null");
        }

        if (to == null) {
            throw new IllegalArgumentException("Целевая единица не должна быть null");
        }

        if (weight < 0) {
            throw new IllegalArgumentException("Значение веса не может быть отрицательным");
        }

        double inGrams = weight * from.getFactorToGram();
        return inGrams / to.getFactorToGram();
    }
}