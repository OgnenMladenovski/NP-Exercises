//Zadacha 10/51
package Generics.ExamExercies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Triple<T extends Number & Comparable<T>> {
    private T first;
    private T second;
    private T third;

    public Triple(T first, T second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public double max()
    {
        double max = 0;
        if(first.compareTo(second) >= 0)
        {
            if(first.compareTo(third) >= 0)
            {
                max = first.doubleValue();
            }
            else
                max = third.doubleValue();
        }
        else if(second.compareTo(third) >= 0)
        {
            if(second.compareTo(first) >= 0)
            {
                max = second.doubleValue();
            }
            else
                max = first.doubleValue();
        }
        else if(third.compareTo(second) >= 0)
        {
            if(third.compareTo(first) >= 0)
            {
                max = third.doubleValue();
            }
            else
                max = first.doubleValue();
        }
        return max;
    }

    public double avarage() {
        double total = first.doubleValue() + second.doubleValue() + third.doubleValue();
        return total / 3;
    }

    public void sort() {
        if(first.compareTo(second) > 0) {
            T temp = first;
            first = second;
            second = temp;
        }
        if (second.compareTo(third) > 0) {
            T temp = second;
            second = third;
            third = temp;
        }
        if (first.compareTo(second) > 0) {
            T temp = first;
            first = second;
            second = temp;
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f", first.doubleValue(), second.doubleValue(), third.doubleValue());
    }
}

public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.avarage());
        tInt.sort();
        System.out.println(tInt);
        float fa = scanner.nextFloat();
        float fb = scanner.nextFloat();
        float fc = scanner.nextFloat();
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.avarage());
        tFloat.sort();
        System.out.println(tFloat);
        double da = scanner.nextDouble();
        double db = scanner.nextDouble();
        double dc = scanner.nextDouble();
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.avarage());
        tDouble.sort();
        System.out.println(tDouble);
    }
}



