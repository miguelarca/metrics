package com.example.metrics.statistics;

public interface Statistic {
    StatisticReport getReport(Sequence<Double> values);
}
