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
    void singleVariableMinimization(Function<Double, Double> singleVariableUniModalFunction, Interval searchInterval, Double expectedMinimizer) {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Double actualMinimizer = fibonacciSearch.minimize(singleVariableUniModalFunction, searchInterval);
        assertEquals(expectedMinimizer, actualMinimizer, 0.1);
    }

    private static Stream<Arguments> singleVariableUniModalFunctions() {
        return Stream.of(
                Arguments.of((Function<Double, Double>) ((Double x) -> Math.pow(x, 2)), new Interval(-10.0, 10.0), 0.0),
                Arguments.of((Function<Double, Double>) ((Double x) -> Math.pow(x-1, 2)), new Interval(-10.0, 10.0), 1.0),
                Arguments.of((Function<Double, Double>) ((Double x) -> 2*Math.pow(x, 2) - Math.log(x)), new Interval(0.0, 10.0), 0.538)
        );
    }
}
