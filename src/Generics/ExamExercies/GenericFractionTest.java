//Zadacha 12/51
package Generics.ExamExercies;

import java.util.Scanner;


class ZeroDenominatorException extends Exception {
    ZeroDenominatorException(String message) {
        super("Denominator cannot be zero");
    }
}

class GenericFraction<T extends Number, U extends Number> {
    private T numerator;
    private U denominator;

    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
        if (denominator.doubleValue() == 0) {
            throw new ZeroDenominatorException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {
        Double new_numerator = (this.numerator.doubleValue() * gf.denominator.doubleValue() + gf.numerator.doubleValue() * this.denominator.doubleValue());
        Double new_denominator = (this.denominator.doubleValue() * gf.denominator.doubleValue());
        GenericFraction<Double, Double> dropka = new GenericFraction<Double, Double>(new_numerator, new_denominator);
        return dropka;
    }

    public double toDouble() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    private long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    @Override
    public String toString() {
        long g = gcd(Math.round(numerator.doubleValue()), Math.round(denominator.doubleValue()));
        return String.format("%.2f / %.2f", numerator.doubleValue() / g, denominator.doubleValue() / g);
    }
}

public class GenericFractionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch (ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}