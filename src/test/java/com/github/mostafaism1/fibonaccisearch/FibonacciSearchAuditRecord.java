package com.github.mostafaism1.fibonaccisearch;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FibonacciSearchAuditRecord {

    private final List<FibonacciSearchIterationAuditRecord> iterationAuditRecords = new ArrayList<>();

    public void setIterationAuditRecords(int iteration, FibonacciSearchIterationAuditRecord iterationAuditRecord) {
        iterationAuditRecords.add(iteration,  iterationAuditRecord);
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

    record FibonacciSearchIterationAuditRecord(Interval searchInterval, InteriorPoints interiorPoints, List<Double> objectiveValues, boolean shiftRight) {

    }
}
