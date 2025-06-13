package fractionmodel;

public class Fraction {
    public final int numerator;
    public final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}