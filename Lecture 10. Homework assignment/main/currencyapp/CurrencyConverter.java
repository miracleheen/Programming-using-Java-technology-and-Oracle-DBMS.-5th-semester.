package main.currencyapp;

public class CurrencyConverter {
    public static double convert(double amount, Currency from, Currency to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Валюта не должна быть null");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }

        double amountInUSD = amount * from.getRateToUSD();
        return amountInUSD / to.getRateToUSD();
    }
}