package main.shapeapp;

class ShapeAreaCalculator {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Triangle(3, 4),
                new Rectangle(5, 6),
                new Square(7),
                new Rhombus(4, 5)
        };

        for (var s : shapes) {
            System.out.println(s.getClass().getSimpleName() + " area: " + s.getArea());
        }
    }
}