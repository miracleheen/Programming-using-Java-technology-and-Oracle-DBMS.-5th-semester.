package main.currencyapp;

public enum Currency {
    USD(1.0),    // базовая валюта
    EUR(1.1),    // 1  => 1.1 usd
    GBP(1.3),    // 1  => 1.3 usd
    JPY(0.009);  // 1  => 0.009 usd

    private final double rateToUSD;

    Currency(double rateToUSD) {
        if (rateToUSD <= 0) {
            throw new IllegalArgumentException("Курс валюты должен быть положительным");
        }
        this.rateToUSD = rateToUSD;
    }

    public double getRateToUSD() {
        return rateToUSD;
    }
}