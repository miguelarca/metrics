package com.example.metrics.statistics;

import java.util.Arrays;

final class Min implements Statistic {
    private final static String message = "This is the minimum value";

    @Override
    public double getValue(double[] values) {
        Arrays.sort(values);

        return values[0];
    }

    @Override
    public StatisticReport getReport(double[] values) {
        return SimpleReport.of(message, this.getValue(values));
    }
}
