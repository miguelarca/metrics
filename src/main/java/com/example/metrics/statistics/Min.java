package com.example.metrics.statistics;

import org.springframework.stereotype.Component;

@Component
class Min implements Statistic {
    private final static String message = "This is the minimum value";

    private double getValue(Sequence<Double> values) {

        return values.getFirst();
    }

    @Override
    public StatisticReport getReport(Sequence<Double> values) {
        return SimpleReport.of(message, this.getValue(values));
    }
}
