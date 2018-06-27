package com.example.metrics.statistics;

public interface Statistic {
    double getValue(double[] values);
    StatisticReport getReport(double[] values);
}
