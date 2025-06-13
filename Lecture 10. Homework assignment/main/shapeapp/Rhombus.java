package main.shapeapp;

public class Rhombus implements Shape {
    private final double diagonal1;
    private final double diagonal2;

    public Rhombus(double diagonal1, double diagonal2) {
        if (diagonal1 <= 0 || diagonal2 <= 0) {
            throw new IllegalArgumentException("Диагонали должны быть положительными");
        }
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
    }

    @Override
    public double getArea() {
        return (diagonal1 * diagonal2) / 2.0;
    }
}
