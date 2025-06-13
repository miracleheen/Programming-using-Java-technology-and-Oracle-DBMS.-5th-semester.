package tests;

import main.currencyapp.Currency;
import main.currencyapp.CurrencyConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {
    private static final double DELTA = 1e-6;

    @Test
    void testConvertUSDToEUR() {
        double result = CurrencyConverter.convert(100, Currency.USD, Currency.EUR);
        assertEquals(100 * Currency.USD.getRateToUSD() / Currency.EUR.getRateToUSD(), result, DELTA);
    }

    @Test
    void testConvertEURToUSD() {
        double result = CurrencyConverter.convert(100, Currency.EUR, Currency.USD);
        assertEquals(100 * Currency.EUR.getRateToUSD() / Currency.USD.getRateToUSD(), result, DELTA);
    }

    @Test
    void testConvertGBPToJPY() {
        double result = CurrencyConverter.convert(50, Currency.GBP, Currency.JPY);
        assertEquals(50 * Currency.GBP.getRateToUSD() / Currency.JPY.getRateToUSD(), result, DELTA);
    }

    @Test
    void testConvertJPYToGBP() {
        double result = CurrencyConverter.convert(10000, Currency.JPY, Currency.GBP);
        assertEquals(10000 * Currency.JPY.getRateToUSD() / Currency.GBP.getRateToUSD(), result, DELTA);
    }

    @Test
    void testInvalidAmount() {
        assertThrows(IllegalArgumentException.class, () ->
                CurrencyConverter.convert(0, Currency.USD, Currency.EUR));
        assertThrows(IllegalArgumentException.class, () ->
                CurrencyConverter.convert(-5, Currency.EUR, Currency.USD));
    }

    @Test
    void testNullCurrency() {
        assertThrows(IllegalArgumentException.class, () ->
                CurrencyConverter.convert(100, null, Currency.USD));
        assertThrows(IllegalArgumentException.class, () ->
                CurrencyConverter.convert(100, Currency.GBP, null));
    }

    @Test
    void testCurrencyEnumValidRates() {
        for (Currency c : Currency.values()) {
            assertTrue(c.getRateToUSD() > 0, "Курс " + c + " должен быть положительным");
        }
    }
}