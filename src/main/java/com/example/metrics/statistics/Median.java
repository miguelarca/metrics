package com.example.metrics.statistics;

import org.springframework.stereotype.Component;

@Component
class Median implements Statistic {
    private final static String message = "This is the median value";

    private double getValue(Sequence<Double> values) {

        if (values.size() % 2 != 0){
            return values.getElement(values.size() / 2);
        }

        return (values.getElement((values.size() - 1) / 2) + values.getElement(values.size()  / 2)) / 2.0;

    }

    @Override
    public StatisticReport getReport(Sequence<Double> values) {
        return SimpleReport.of(message, this.getValue(values));
    }

}
