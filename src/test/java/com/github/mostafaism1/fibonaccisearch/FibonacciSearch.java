package com.github.mostafaism1.fibonaccisearch;

import java.util.List;
import java.util.function.Function;

public class FibonacciSearch {
    private static final List<Integer> FIBONACCI_SEQUENCE = List.of(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);

    public Double minimize(Function<Double, Double> f, Interval initialSearchInterval) {
        Interval currentInterval = initialSearchInterval;
        InteriorPoints interiorPoints;
        for (int i = FIBONACCI_SEQUENCE.size() - 1; i > 1; i--) {
            interiorPoints = computeInteriorPoints(currentInterval, i);
            Double fx1 = f.apply(interiorPoints.lower());
            Double fx2 = f.apply(interiorPoints.upper());
            if (fx1 < fx2) {
                currentInterval = new Interval(currentInterval.start(), interiorPoints.upper());
            }
            if (fx1 > fx2) {
                currentInterval = new Interval(interiorPoints.lower(), currentInterval.end());
            }
        }
        return (currentInterval.start() + currentInterval.end())/2;
    }

    private static InteriorPoints computeInteriorPoints(Interval currentInterval, int fibIndex) {
        InteriorPoints interiorPoints;
        Integer fibN = FIBONACCI_SEQUENCE.get(fibIndex);
        Integer fibNMinusOne = FIBONACCI_SEQUENCE.get(fibIndex -1);
        Integer fibNMinusTwo = FIBONACCI_SEQUENCE.get(fibIndex -2);
        Double r1 = (double)fibNMinusTwo / fibN;
        Double r2 = (double)fibNMinusOne / fibN;
        interiorPoints = new InteriorPoints(
                currentInterval.start() + r1 * (currentInterval.end()- currentInterval.start()),
                currentInterval.start() + r2 * (currentInterval.end()- currentInterval.start()));
        return interiorPoints;
    }

    private static record InteriorPoints(Double lower, Double upper) {}
}
