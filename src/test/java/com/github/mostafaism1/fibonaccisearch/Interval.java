package com.github.mostafaism1.fibonaccisearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record Interval (Point start, Point end) {

    public Double length() {
        return start.distanceTo(end);
    }

    public List<Double> averagePoint() {
        return start.add(end).midPoint().coordinates();
    }
}
