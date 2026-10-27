package com.github.mostafaism1.fibonaccisearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FibonacciSearchAcceptanceTest {

    @Test
    void minimizeXSquared() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        assertEquals(0, fibonacciSearch.minimize((Double x) -> x*x), 0.01);
    }

    @Test
    void minimizeXMinusOneSquared() {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        assertEquals(1, fibonacciSearch.minimize((Double x) -> (x-1)*(x-1)), 0.1);
    }
}
