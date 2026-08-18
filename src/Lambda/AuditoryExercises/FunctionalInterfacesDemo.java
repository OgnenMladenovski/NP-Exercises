package Lambda.AuditoryExercises;

import java.time.LocalDate;
import java.util.function.*;

public class FunctionalInterfacesDemo {
    public static void main(String[] args) {

        //Function - Za merenje na dolzhina na string
        Function<String, Integer> stringLength = str -> str.length();
        System.out.println("The length of 'Hello' is: " + stringLength.apply("Hello"));

        System.out.println("==================================");

        //BiFunction - Za kalkulacii na 2 integeri (se pishuva so 3 Integer za kakov e ochekuvaniot rezultat)
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        System.out.println("The sum of 5 and 8 is: " + sum.apply(5,8));

        System.out.println("==================================");

        //Predicate - Za proverka na kalkulacii (so odgovor true ili false)
        Predicate<Integer> isEven = number -> number % 2 == 0;
        System.out.println("Is 7 even? - " + isEven.test(7));
        System.out.println("Is 16 even? - " + isEven.test(16));

        System.out.println("==================================");

        //Consumer - Za printanje na stringovi
        Consumer<String> printString = str -> System.out.println("Printing: " + str);
        printString.accept("Hello World");

        System.out.println("==================================");

        //Supplier - Za pechatenje vreme vo milisekundi
        Supplier<LocalDate> currentDate = () -> LocalDate.now();
        System.out.println("Current date is: " + currentDate.get());
    }
}
