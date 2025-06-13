package main.weightapp;

public enum WeightUnit {
    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    TONNE(1_000_000.0);

    private final double factorToGram;

    WeightUnit(double factorToGram) {
        if (factorToGram <= 0) {
            throw new IllegalArgumentException("Коэффициент должен быть положительным");
        }
        this.factorToGram = factorToGram;
    }

    public double getFactorToGram() {
        return factorToGram;
    }
}