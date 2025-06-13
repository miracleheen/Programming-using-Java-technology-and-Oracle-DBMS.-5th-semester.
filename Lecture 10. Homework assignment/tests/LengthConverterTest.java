package tests;

import main.lenghtapp.LengthConverter;
import main.lenghtapp.LengthUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LengthConverterTest {
    private static final double DELTA = 1e-6;

    @Test
    void testConvertMillimeterToMeter() {
        double res = LengthConverter.convert(1000, LengthUnit.MILLIMETER, LengthUnit.METER);
        assertEquals(1.0, res, DELTA);
    }

    @Test
    void testConvertKilometerToMeter() {
        double res = LengthConverter.convert(2.5, LengthUnit.KILOMETER, LengthUnit.METER);
        assertEquals(2500.0, res, DELTA);
    }

    @Test
    void testConvertCentimeterToDecimeter() {
        double res = LengthConverter.convert(123, LengthUnit.CENTIMETER, LengthUnit.DECIMETER);
        // 123 см = 1.23 м; 1.23 / 0.1 = 12.3 дм
        assertEquals(12.3, res, DELTA);
    }

    @Test
    void testConvertMeterToKilometer() {
        double res = LengthConverter.convert(500, LengthUnit.METER, LengthUnit.KILOMETER);
        assertEquals(0.5, res, DELTA);
    }

    @Test
    void testConvertZero() {
        double res = LengthConverter.convert(0, LengthUnit.METER, LengthUnit.KILOMETER);
        assertEquals(0.0, res, DELTA);
    }

    @Test
    void testInvalidNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                LengthConverter.convert(-1, LengthUnit.METER, LengthUnit.CENTIMETER));
    }

    @Test
    void testNullUnits() {
        assertThrows(IllegalArgumentException.class, () ->
                LengthConverter.convert(10, null, LengthUnit.METER));
        assertThrows(IllegalArgumentException.class, () ->
                LengthConverter.convert(10, LengthUnit.CENTIMETER, null));
    }

    @Test
    void testEnumFactorsPositive() {
        for (LengthUnit u : LengthUnit.values()) {
            assertTrue(u.getFactorToMeter() > 0,
                    "Коэффициент должен быть положительным для " + u);
        }
    }
}