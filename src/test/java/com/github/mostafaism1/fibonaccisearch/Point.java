package com.github.mostafaism1.fibonaccisearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Point {
    private final List<Double> coordinates;

    public Point(Double... coordinates) {
        this.coordinates = new ArrayList<>(Arrays.asList(coordinates));
    }

    private Point(List<Double> coordinates) {
        this.coordinates = new ArrayList<>(coordinates);
    }

    public List<Double> coordinates() {
        return coordinates;
    }

    public Double distanceTo(Point p2) {
        Double sumOfDifferenceSquared = 0.0;
        for (int i = 0; i < coordinates().size() ; i++) {
            sumOfDifferenceSquared += Math.pow(p2.coordinates().get(i) - coordinates().get(i), 2);
        }
        return Math.sqrt(sumOfDifferenceSquared);
    }

    public Point add(Point p2) {
        List<Double> sumCoordinates = new ArrayList<>();
        for (int i = 0; i < coordinates().size() ; i++) {
            sumCoordinates.add(coordinates.get(i) + p2.coordinates().get(i));
        }
        return new Point(sumCoordinates);
    }

    public Point midPoint() {
        List<Double> midPointCoordinates = coordinates().stream()
                .map(x -> x/2)
                .collect(Collectors.toList());
        return new Point(midPointCoordinates);
    }

    public Point scale(double factor) {
        List<Double> scaledCoordinates = coordinates().stream()
                .map(x -> factor*x)
                .collect(Collectors.toList());
        return new Point(scaledCoordinates);
    }

    public Point negative() {
        List<Double> negativeCoordinates = coordinates().stream()
                .map(x -> x == 0 ? 0 : -1*x)
                .collect(Collectors.toList());
        return new Point(negativeCoordinates);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point))
            return false;
        Point other = (Point) obj;
        return coordinates.equals(other.coordinates);
    }

    @Override
    public int hashCode() {
        return this.coordinates().hashCode();
    }

    @Override
    public String toString() {
        // cover the coordinates from List<Double> to a vector representation in bracket.
        return coordinates().stream().map(coordinate -> String.format("%.2f", coordinate)).collect(Collectors.joining(",", "(", ")"));
    }
}
