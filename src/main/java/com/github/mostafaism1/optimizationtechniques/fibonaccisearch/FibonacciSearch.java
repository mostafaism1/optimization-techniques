package com.github.mostafaism1.optimizationtechniques.fibonaccisearch;

import com.github.mostafaism1.optimizationtechniques.fibonaccisearch.FibonacciSearchAuditRecord.FibonacciSearchIterationAuditRecord;

import java.util.List;
import java.util.function.Function;

public class FibonacciSearch {

    private static final int ITERATION_BUFFER_TO_COMPENSATE_FOR_LOSSY_DOUBLE_PRECISION = 0;

    public FibonacciSearchResult minimize(Function<Point, Double> f, Interval initialSearchInterval, Double accuracy) {
        Double reductionRatio = computeReductionRatio(initialSearchInterval, accuracy);
        int leastFibonacciForReductionRatio = computeLeastFibonacciForReductionRatio(reductionRatio);
        Interval currentInterval = initialSearchInterval;
        InteriorPoints interiorPoints;
        FibonacciSearchAuditRecord fibonacciSearchAuditRecord = new FibonacciSearchAuditRecord();
        for (int i = leastFibonacciForReductionRatio + ITERATION_BUFFER_TO_COMPENSATE_FOR_LOSSY_DOUBLE_PRECISION; i >= 3; i--) {
            interiorPoints = computeInteriorPoints(currentInterval, i);
            Double fx1 = f.apply(interiorPoints.lower());
            Double fx2 = f.apply(interiorPoints.upper());
            boolean shiftRight = fx1 < fx2;
            fibonacciSearchAuditRecord.setIterationAuditRecords(
                leastFibonacciForReductionRatio + ITERATION_BUFFER_TO_COMPENSATE_FOR_LOSSY_DOUBLE_PRECISION - i,
                new FibonacciSearchIterationAuditRecord(currentInterval, interiorPoints, List.of(fx1, fx2), shiftRight));
            if (shiftRight) {
                currentInterval = new Interval(currentInterval.start(), interiorPoints.upper());
            }
            else {
                currentInterval = new Interval(interiorPoints.lower(), currentInterval.end());
            }
        }
        System.out.println(fibonacciSearchAuditRecord);
        return new FibonacciSearchResult(currentInterval.averagePoint(), fibonacciSearchAuditRecord);
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
        Integer fibN = computeNthFibonacci(fibIndex);
        Integer fibNMinusOne = computeNthFibonacci(fibIndex -1);
        Integer fibNMinusTwo = computeNthFibonacci(fibIndex -2);
        Double r1 = (double)fibNMinusTwo / fibN;
        Double r2 = (double)fibNMinusOne / fibN;
        InteriorPoints interiorPoints = new InteriorPoints(
                currentInterval.start().add(currentInterval.end().add(currentInterval.start().negative()).scale(r1)),
                currentInterval.start().add(currentInterval.end().add(currentInterval.start().negative()).scale(r2))
        );
        return interiorPoints;
    }

}
