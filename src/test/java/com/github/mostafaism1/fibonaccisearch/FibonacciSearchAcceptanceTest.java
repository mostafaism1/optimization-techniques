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
    void singleVariableMinimization(Function<Point, Double> singleVariableUniModalFunction, Interval searchInterval, Double accuracy, Point expectedMinimizer) {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        FibonacciSearchResult result = fibonacciSearch.minimize(singleVariableUniModalFunction, searchInterval, accuracy);
        Point actualMinimizer = result.minimizer();
        Double error = actualMinimizer.distanceTo(expectedMinimizer);
        assertEquals(accuracy, error, accuracy);
    }

    @DisplayName("Multi Variable Objective Minimization")
    @ParameterizedTest
    @MethodSource("multiVariableUniModalFunctions")
    void multiVariableMinimization(Function<Point, Double> multiVariableUniModalFunction, Interval searchInterval, Double accuracy, Point expectedMinimizer) {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        FibonacciSearchResult result = fibonacciSearch.minimize(multiVariableUniModalFunction, searchInterval, accuracy);
        Point actualMinimizer = result.minimizer();
        Double error = actualMinimizer.distanceTo(expectedMinimizer);
        assertEquals(accuracy, error, accuracy);
    }

    private static Stream<Arguments> singleVariableUniModalFunctions() {
        return Stream.of(
                Arguments.of((Function<Point, Double>) ((Point p) -> Math.pow(p.coordinates().get(0), 2)), new Interval(new Point(-10.0), new Point(10.0)), 0.001, new Point(0.0)),
                Arguments.of((Function<Point, Double>) ((Point p) -> Math.pow(p.coordinates().get(0)-1, 2)), new Interval(new Point(-10.0), new Point(10.0)), 0.001, new Point(1.0)),
                Arguments.of((Function<Point, Double>) ((Point p) -> 2*Math.pow(p.coordinates().get(0), 2) - Math.log(p.coordinates().get(0))), new Interval(new Point(0.0), new Point(10.0)), 0.001, new Point(0.5))
                );
    }

    public static Stream<Arguments> multiVariableUniModalFunctions() {
        return Stream.of(
                Arguments.of((Function<Point, Double>) ((Point p) -> {
                    Double x = p.coordinates().get(0);
                    Double y = p.coordinates().get(1);
                    return Math.pow(x, 2) + Math.pow(y, 2);
                }), new Interval(new Point(-10.0, -10.0), new Point(10.0, 10.0)), 0.001, new Point(0.0, 0.0)),
                Arguments.of((Function<Point, Double>) ((Point p) -> {
                    Double x = p.coordinates().get(0);
                    Double y = p.coordinates().get(1);
                    return Math.pow(x-1, 2) + Math.pow(y-1, 2);
                }), new Interval(new Point(-10.0, -10.0), new Point(10.0, 10.0)), 0.001, new Point(1.0, 1.0)),
                Arguments.of((Function<Point, Double>) ((Point p) -> {
                    Double x = p.coordinates().get(0);
                    Double y = p.coordinates().get(1);
                    Double z = p.coordinates().get(2);
                    // 14x^2 + y^2 + z^2 - 4xy - 6xz - 3x
                    return 14*Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2) - 4*x*y - 6*x*z - 3*x;
                }), new Interval(new Point(0.0, 2.0, 4.0), new Point(2.0, 5.0, 8.0)), 0.05, new Point(2.0, 5.0, 8.0))
        );
    }
}
