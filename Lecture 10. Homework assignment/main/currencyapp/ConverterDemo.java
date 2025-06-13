package main.currencyapp;

public class ConverterDemo {
    public static void main(String[] args) {
        double sum1 = 100;
        System.out.printf("%.2f USD => EUR = %.2f EUR%n", sum1,
                CurrencyConverter.convert(sum1, Currency.USD, Currency.EUR));

        double sum2 = 50;
        System.out.printf("%.2f EUR => GBP = %.2f GBP%n", sum2,
                CurrencyConverter.convert(sum2, Currency.EUR, Currency.GBP));

        double sum3 = 10000;
        System.out.printf("%.2f JPY => USD = %.2f USD%n", sum3,
                CurrencyConverter.convert(sum3, Currency.JPY, Currency.USD));

        double sum4 = 20;
        System.out.printf("%.2f GBP => JPY = %.2f JPY%n", sum4,
                CurrencyConverter.convert(sum4, Currency.GBP, Currency.JPY));

    }
}
