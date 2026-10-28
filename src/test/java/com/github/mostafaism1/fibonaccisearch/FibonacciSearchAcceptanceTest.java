package com.github.mostafaism1.fibonaccisearch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Fibonacci Search")
public class FibonacciSearchAcceptanceTest {

    @DisplayName("Single Variable Objective Minimization")
    @ParameterizedTest
    @MethodSource("singleVariableUniModalFunctions")
    void singleVariableMinimization(Function<Point, Double> singleVariableUniModalFunction, Interval searchInterval, Double accuracy, Double expectedMinimizer) {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        Double actualMinimizer = fibonacciSearch.minimize(singleVariableUniModalFunction, searchInterval, accuracy).get(0);
        assertEquals(expectedMinimizer, actualMinimizer, accuracy);
    }

//    @DisplayName("Multi Variable Objective Minimization")
//    @ParameterizedTest
//    @MethodSource("multiVariableUniModalFunctions")
//    void multiVariableMinimization(Function<List<Double>, Double> multiVariableUniModalFunction, Interval searchInterval, Double accuracy, Double expectedMinimizer) {
//        FibonacciSearch fibonacciSearch = new FibonacciSearch();
//        Double actualMinimizer = fibonacciSearch.minimize(multiVariableUniModalFunction, searchInterval, accuracy);
//        assertEquals(expectedMinimizer, actualMinimizer, accuracy);
//    }

    private static Stream<Arguments> singleVariableUniModalFunctions() {
        return Stream.of(
                Arguments.of((Function<Point, Double>) ((Point p) -> Math.pow(p.coordinates().get(0), 2)), new Interval(new Point(List.of(-10.0)), new Point(List.of(10.0))), 0.001, 0.0),
                Arguments.of((Function<Point, Double>) ((Point p) -> Math.pow(p.coordinates().get(0)-1, 2)), new Interval(new Point(List.of(-10.0)), new Point(List.of(10.0))), 0.001, 1.0),
                Arguments.of((Function<Point, Double>) ((Point p) -> 2*Math.pow(p.coordinates().get(0), 2) - Math.log(p.coordinates().get(0))), new Interval(new Point(List.of(0.0)), new Point(List.of(10.0))), 0.001, 0.5)
        );
    }

    public static Stream<Arguments> multiVariableUniModalFunctions() {
        return Stream.of(
                Arguments.of((Function<List<Double>, Double>) ((List<Double> args) -> {
                    Double x = args.get(0);
                    Double y = args.get(1);
                    return Math.pow(x, 2) + Math.pow(y, 2);
                }), new Interval(new Point(List.of(-1.0, -1.0)), new Point(List.of(1.0, 1.0))), 0.001, 0.0)
        );
    }
}
