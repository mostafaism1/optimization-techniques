package com.github.mostafaism1.fibonaccisearch;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciSearchAuditAcceptanceTest {

    @Test
    public void abc() {
        FibonacciSearchResult result = new FibonacciSearch().minimize((Function<Point, Double>) ((Point p) -> Math.pow(p.coordinates().get(0), 2)), new Interval(new Point(List.of(-10.0)), new Point(List.of(10.0))), 0.001);
        FibonacciSearchAuditRecord auditRecord = result.auditRecord();
        assertEquals(new Point(List.of(-10.0)), auditRecord.searchIterval(0).start());
    }
}
