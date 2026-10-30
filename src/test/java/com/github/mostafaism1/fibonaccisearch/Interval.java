package com.github.mostafaism1.fibonaccisearch;

public record Interval (Point start, Point end) {

    public Double length() {
        return start.distanceTo(end);
    }

    public Point averagePoint() {
        return start.add(end).midPoint();
    }
}
