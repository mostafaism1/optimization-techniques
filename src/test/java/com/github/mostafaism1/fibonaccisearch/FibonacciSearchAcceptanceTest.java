package com.github.mostafaism1.fibonaccisearch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Fibonacci Search")
public class FibonacciSearchAcceptanceTest {

    @ParameterizedTest
    @MethodSource("singleVariableUniModalFunctions")
    void singleVariableMinimization(Function<Double, Double> singleVariableUniModalFunction, Double expectedMinimizer) {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Double actualMinimizer = fibonacciSearch.minimize(singleVariableUniModalFunction, new Interval(-10.0, 10.0));
        assertEquals(expectedMinimizer, actualMinimizer, 0.1);
    }

    private static Stream<Arguments> singleVariableUniModalFunctions() {
        return Stream.of(
                Arguments.of((Function<Double, Double>) ((Double x) -> x*x), 0.0),
                Arguments.of((Function<Double, Double>) ((Double x) -> (x-1)*(x-1)), 1.0)
        );
    }
}
