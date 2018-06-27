package com.example.metrics.statistics;

import java.util.Arrays;

final class Median implements Statistic {
    private final static String message = "This is the median value";

    @Override
    public double getValue(double[] values) {
        Arrays.sort(values);

        if (values.length % 2 != 0)
            return values[values.length / 2];

        return (values[(values.length - 1) / 2] + values[values.length / 2]) / 2.0;

    }

    @Override
    public StatisticReport getReport(double[] values) {
        return SimpleReport.of(message, this.getValue(values));
    }

}
