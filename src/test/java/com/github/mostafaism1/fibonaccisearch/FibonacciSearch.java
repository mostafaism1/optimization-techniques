package com.github.mostafaism1.fibonaccisearch;

import java.util.List;
import java.util.function.Function;

public class FibonacciSearch {

    public Double minimize(Function<Double, Double> f, Interval initialSearchInterval, Double accuracy) {
        Double reductionRatio = computeReductionRatio(initialSearchInterval, accuracy);
        int leastFibonacciForReductionRatio = computeLeastFibonacciForReductionRatio(reductionRatio);
        Interval currentInterval = initialSearchInterval;
        InteriorPoints interiorPoints;
        for (int i = leastFibonacciForReductionRatio; i >= 3; i--) {
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

    private static int computeLeastFibonacciForReductionRatio(Double reductionRatio) {
        int currentFibonacciIndex = 0;
        while(true) {
            int currentFibonacci = computeNthFibonacci(currentFibonacciIndex);
            if (currentFibonacci >= reductionRatio)
                return currentFibonacciIndex;
            currentFibonacciIndex++;
        }
    }

    private static int computeNthFibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        int a =  0;
        int b = 1;
        int result = 0;
        for (int i = 0; i <= n-2; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }

    private static Double computeReductionRatio(Interval initialSearchInterval, Double accuracy) {
        Double finalIntervalLength = accuracy*2;
        return initialSearchInterval.length()/finalIntervalLength;
    }

    private static InteriorPoints computeInteriorPoints(Interval currentInterval, int fibIndex) {
        InteriorPoints interiorPoints;
        Integer fibN = computeNthFibonacci(fibIndex);
        Integer fibNMinusOne = computeNthFibonacci(fibIndex -1);
        Integer fibNMinusTwo = computeNthFibonacci(fibIndex -2);
        Double r1 = (double)fibNMinusTwo / fibN;
        Double r2 = (double)fibNMinusOne / fibN;
        interiorPoints = new InteriorPoints(
                currentInterval.start() + r1 * (currentInterval.end()- currentInterval.start()),
                currentInterval.start() + r2 * (currentInterval.end()- currentInterval.start()));
        return interiorPoints;
    }

    private static record InteriorPoints(Double lower, Double upper) {}
}
