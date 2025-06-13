package main.shapeapp;

public class Square implements Shape {
    private final double side;

    public Square(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Сторона должна быть положительной");
        }
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }
}
