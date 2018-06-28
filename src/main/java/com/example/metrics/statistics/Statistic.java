package com.example.metrics.statistics;

public interface Statistic {
    StatisticReport getReport(TotalizingSequence<Double> values);
}
