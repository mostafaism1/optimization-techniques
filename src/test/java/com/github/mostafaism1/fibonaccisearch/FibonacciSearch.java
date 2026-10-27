package com.github.mostafaism1.fibonaccisearch;

import java.util.List;
import java.util.function.Function;

public class FibonacciSearch {
    private static final List<Integer> FIBONACCI_SEQUENCE = List.of(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
    public Double minimize(Function<Double, Double> f) {
        Double a = -10.0;
        Double b = 10.0;

        Double x1 = 0.0;
        Double x2 = 0.0;
        for (int i = FIBONACCI_SEQUENCE.size() - 1; i > 1; i--) {
            Integer fibN = FIBONACCI_SEQUENCE.get(i);
            Integer fibNMinusOne = FIBONACCI_SEQUENCE.get(i-1);
            Integer fibNMinusTwo = FIBONACCI_SEQUENCE.get(i-2);
            Double r1 = (double)fibNMinusTwo / fibN;
            Double r2 = (double)fibNMinusOne / fibN;
            x1 = a + r1 * (b-a);
            x2 = a + r2 * (b-a);
            Double fx1 = f.apply(x1);
            Double fx2 = f.apply(x2);
            if (fx1 < fx2) {
                b = x2;
            }
            if (fx1 > fx2) {
                a = x1;
            }
        }
        return (x1 + x2)/2;
    }
}
