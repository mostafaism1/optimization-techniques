package com.github.mostafaism1.fibonaccisearch;

public record Interval (Double start, Double end) {

    public Double length() {
        return end-start;
    }
}
