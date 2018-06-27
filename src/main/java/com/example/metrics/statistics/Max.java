package com.example.metrics.statistics;

import java.util.Arrays;

public class Max implements Statistic {
    private final static String message = "This is the maximum value";

    @Override
    public double getValue(double[] values) {
        Arrays.sort(values);

        return values[values.length - 1];
    }

    @Override
    public StatisticReport getReport(double[] values) {
        return SimpleReport.of(message, this.getValue(values));
    }
}
