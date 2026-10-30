package com.github.mostafaism1.fibonaccisearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FibonacciSearchAuditRecord {

    private final List<FibonacciSearchIterationAuditRecord> iterationAuditRecords = new ArrayList<>();

    public void setIterationAuditRecords(int iteration, FibonacciSearchIterationAuditRecord iterationAuditRecord) {
        iterationAuditRecords.add(iteration, iterationAuditRecord);
    }

    public Interval searchIterval(int iteration) {
        return iterationAuditRecords.get(iteration).searchInterval();
    }

    public InteriorPoints intermediatePoints(int iteration) {
        return iterationAuditRecords.get(iteration).interiorPoints();
    }

    public Double objectiveValues(int iteration, int interiorPointIndex) {
        return iterationAuditRecords.get(iteration).objectiveValues().get(interiorPointIndex);
    }

    public boolean shiftRight(int iteration) {
        return iterationAuditRecords.get(iteration).shiftRight();
    }

    @Override
    public String toString() {
        String header = "iter\ta\tx_1\tx_2\tb\tfx1\tcomp\tfx2";

        String result = IntStream.range(0, iterationAuditRecords.size())
                .mapToObj(i -> (i + 1) + "\t" + iterationAuditRecords.get(i))
                .collect(Collectors.joining(System.lineSeparator(), header + System.lineSeparator(), ""));

        return result;
    }

    record FibonacciSearchIterationAuditRecord(
            Interval searchInterval,
            InteriorPoints interiorPoints,
            List<Double> objectiveValues,
            boolean shiftRight) {

        @Override
        public String toString() {
            return String.format(
                    "%s\t%s\t%s\t%s\t%.3f\t%s\t%.3f",
                    searchInterval.start(),
                    interiorPoints.lower(),
                    interiorPoints.upper(),
                    searchInterval.end(),
                    objectiveValues.get(0),
                    shiftRight ? "<" : ">",
                    objectiveValues.get(1)
            );
        }
    }
}