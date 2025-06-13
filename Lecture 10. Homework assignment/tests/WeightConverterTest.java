package tests;

import main.weightapp.WeightConverter;
import main.weightapp.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeightConverterTest {
    private static final double DELTA = 1e-6;

    @Test
    void testConvertMilligramToGram() {
        double res = WeightConverter.convert(1000, WeightUnit.MILLIGRAM, WeightUnit.GRAM);
        assertEquals(1.0, res, DELTA);
    }

    @Test
    void testConvertGramToKilogram() {
        double res = WeightConverter.convert(5000, WeightUnit.GRAM, WeightUnit.KILOGRAM);
        assertEquals(5.0, res, DELTA);
    }

    @Test
    void testConvertKilogramToTon() {
        double res = WeightConverter.convert(2000, WeightUnit.KILOGRAM, WeightUnit.TONNE);
        // 2000 кг = 2 тонны
        assertEquals(2.0, res, DELTA);
    }

    @Test
    void testConvertTonToGram() {
        double res = WeightConverter.convert(0.5, WeightUnit.TONNE, WeightUnit.GRAM);
        // 0.5 тонны = 500 кг = 500000 г
        assertEquals(500_000.0, res, DELTA);
    }

    @Test
    void testConvertZero() {
        double res = WeightConverter.convert(0, WeightUnit.GRAM, WeightUnit.KILOGRAM);
        assertEquals(0.0, res, DELTA);
    }

    @Test
    void testInvalidNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                WeightConverter.convert(-1, WeightUnit.GRAM, WeightUnit.KILOGRAM));
    }

    @Test
    void testNullUnits() {
        assertThrows(IllegalArgumentException.class, () ->
                WeightConverter.convert(10, null, WeightUnit.GRAM));
        assertThrows(IllegalArgumentException.class, () ->
                WeightConverter.convert(10, WeightUnit.KILOGRAM, null));
    }

    @Test
    void testEnumFactorsPositive() {
        for (WeightUnit u : WeightUnit.values()) {
            assertTrue(u.getFactorToGram() > 0,
                    "Коэффициент должен быть положительным для " + u);
        }
    }
}