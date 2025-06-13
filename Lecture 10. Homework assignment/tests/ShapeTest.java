package tests;

import main.shapeapp.Rectangle;
import main.shapeapp.Rhombus;
import main.shapeapp.Square;
import main.shapeapp.Triangle;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShapeTest {
    @Test
    void testTriangleArea() {
        Triangle t = new Triangle(4, 5);
        assertEquals(10.0, t.getArea(), 1e-6);
    }

    @Test
    void testTriangleInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(4, -1));
    }

    @Test
    void testRectangleArea() {
        Rectangle r = new Rectangle(3, 6);
        assertEquals(18.0, r.getArea(), 1e-6);
    }

    @Test
    void testRectangleInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(3, -2));
    }

    @Test
    void testSquareArea() {
        Square s = new Square(5);
        assertEquals(25.0, s.getArea(), 1e-6);
    }

    @Test
    void testSquareInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Square(0));
        assertThrows(IllegalArgumentException.class, () -> new Square(-3));
    }

    @Test
    void testRhombusArea() {
        Rhombus rh = new Rhombus(4, 6);
        assertEquals(12.0, rh.getArea(), 1e-6);
    }

    @Test
    void testRhombusInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Rhombus(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Rhombus(4, -2));
    }
}