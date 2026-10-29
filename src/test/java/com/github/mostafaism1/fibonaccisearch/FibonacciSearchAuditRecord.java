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

    public static record FibonacciSearchIterationAuditRecord(Interval searchInterval) {}
}
