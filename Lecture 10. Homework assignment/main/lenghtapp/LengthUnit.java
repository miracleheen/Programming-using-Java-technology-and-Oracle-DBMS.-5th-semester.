package main.lenghtapp;

public enum LengthUnit {
    MILLIMETER(0.001),
    CENTIMETER(0.01),
    DECIMETER(0.1),
    METER(1.0),
    KILOMETER(1000.0);

    private final double factorToMeter;

    LengthUnit(double factorToMeter) {
        if (factorToMeter <= 0) {
            throw new IllegalArgumentException("Коэффициент должен быть положительным");
        }
        this.factorToMeter = factorToMeter;
    }

    public double getFactorToMeter() {
        return factorToMeter;
    }
}