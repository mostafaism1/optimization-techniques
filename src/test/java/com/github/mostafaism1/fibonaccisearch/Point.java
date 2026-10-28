package com.github.mostafaism1.fibonaccisearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record Point (List<Double> coordinates) {
    public Double distanceTo(Point p2) {
        Double result = 0.0;
        for (int i = coordinates.size() - 1; i >= 0; i--) {
            result += Math.pow(p2.coordinates().get(i) - coordinates().get(i), 2);
        }
        return result;
    }

    public Point add(Point p2) {
        List<Double> sumCoordinates = new ArrayList<>();
        for (int i = coordinates.size() - 1; i >= 0; i--) {
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
                .map(x -> x*factor)
                .collect(Collectors.toList());
        return new Point(scaledCoordinates);
    }

    public Point negative() {
        List<Double> negativeCoordinates = coordinates().stream()
                .map(x -> -x)
                .collect(Collectors.toList());
        return new Point(negativeCoordinates);
    }
}
