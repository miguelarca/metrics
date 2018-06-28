package com.example.metrics.statistics;

public interface Statistic {
    double getValue(Sequence<Double> values);
    StatisticReport getReport(Sequence<Double> values);
}
