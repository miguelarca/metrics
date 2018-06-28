package com.example.metrics.statistics;

public class Max implements Statistic {
    private final static String message = "This is the maximum value";

    @Override
    public double getValue(Sequence<Double> values) {
        return values.getLast();
    }

    @Override
    public StatisticReport getReport(Sequence<Double> values) {
        return SimpleReport.of(message, this.getValue(values));
    }
}
