package utils;

import fractionmodel.Fraction;

import java.util.function.BiFunction;

public class FractionUtilsOperations {
    private static final BiFunction<Fraction, Fraction, Fraction> sumLambda = (f1, f2) -> {
        int numerator = f1.numerator * f2.denominator + f2.numerator * f1.denominator;
        int denominator = f1.denominator * f2.denominator;
        return new Fraction(numerator, denominator);
    };

    private static final BiFunction<Fraction, Fraction, Fraction> differenceLambda = (f1, f2) -> {
        int numerator = f1.numerator * f2.denominator - f2.numerator * f1.denominator;
        int denominator = f1.denominator * f2.denominator;
        return new Fraction(numerator, denominator);
    };

    private static final BiFunction<Fraction, Fraction, Fraction> productLambda = (f1, f2) -> {
        int numerator = f1.numerator * f2.numerator;
        int denominator = f1.denominator * f2.denominator;
        return new Fraction(numerator, denominator);
    };

    private static final BiFunction<Fraction, Fraction, Fraction> divisionLambda = (f1, f2) -> {
        int numerator = f1.numerator * f2.denominator;
        int denominator = f1.denominator * f2.numerator;
        return new Fraction(numerator, denominator);
    };

    public static Fraction sum(Fraction f1, Fraction f2) {
        return sumLambda.apply(f1, f2);
    }

    public static Fraction difference(Fraction f1, Fraction f2) {
        return differenceLambda.apply(f1, f2);
    }

    public static Fraction product(Fraction f1, Fraction f2) {
        return productLambda.apply(f1, f2);
    }

    public static Fraction division(Fraction f1, Fraction f2) {
        return divisionLambda.apply(f1, f2);
    }
}
