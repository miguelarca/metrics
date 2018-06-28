package com.example.metrics.statistics;

import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

@Component
class Mean implements Statistic {
    @Override
    public StatisticReport getReport(TotalizingSequence<Double> values) {
        int totalElements = values.size();

        return SimpleReport.of( "This is the mean value", values.sum() / totalElements);
    }

}
