package com.github.mostafaism1.fibonaccisearch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Fibonacci Search Auditing")
public class FibonacciSearchAuditingAcceptanceTest {

    @DisplayName("Record per Iteration Computations")
    @ParameterizedTest
    @MethodSource("source")
    public void recordPerIterationComputations(Function<Point, Double> unimodalFunction, Interval searchInterval, Double accuracy, int iteration, Double searchIntervalLowerBound, Double searchIntervalUpperBound, Double lowerInteriorPoint, Double upperInteriorPoint, Double objectiveAtLowerInteriorPoint, Double  objectiveAtUpperInteriorPoint, boolean shiftRight) {
        FibonacciSearchResult result = new FibonacciSearch().minimize(unimodalFunction, searchInterval, accuracy);
        FibonacciSearchAuditRecord auditRecord = result.auditRecord();
        assertEquals(new Point(searchIntervalLowerBound), auditRecord.searchIterval(iteration).start());
        assertEquals(new Point(searchIntervalUpperBound), auditRecord.searchIterval(iteration).end());
        assertEquals(new Point(lowerInteriorPoint), auditRecord.intermediatePoints(iteration).lower());
        assertEquals(new Point(upperInteriorPoint), auditRecord.intermediatePoints(iteration).upper());
        assertEquals(objectiveAtLowerInteriorPoint, auditRecord.objectiveValues(iteration, 0), 0.001);
        assertEquals(objectiveAtUpperInteriorPoint, auditRecord.objectiveValues(iteration, 1), 0.001);
        assertEquals(shiftRight, auditRecord.shiftRight(iteration));
    }


    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of((Function<Point, Double>) ((Point p) -> Math.pow(p.coordinates().get(0), 2)),
                        new Interval(new Point(-10.0), new Point(10.0)),
                        0.001,
                        0, -10.0, 10.0, -2.36, 2.36, 5.573, 5.573, false),
                Arguments.of((Function<Point, Double>) ((Point p) -> Math.pow(p.coordinates().get(0), 2)),
                        new Interval(new Point(-10.0), new Point(10.0)),
                        0.001,
                        1, -2.36, 10.00, 2.36, 5.28, 5.573, 27.864, true),
                Arguments.of((Function<Point, Double>) ((Point p) -> Math.pow(p.coordinates().get(0), 2)),
                        new Interval(new Point(-10.0), new Point(10.0)),
                        0.001,
                        10, -0.13, 0.03, -0.07, -0.03, 0.005, 0.001, false)
        );
    }
}
