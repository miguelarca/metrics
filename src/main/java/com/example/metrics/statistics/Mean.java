package com.example.metrics.statistics;

import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

@Component
class Mean implements Statistic {
    @Override
    public StatisticReport getReport(Sequence<Double> values) {
        int totalElements = values.size();

        Double sum = StreamSupport.stream(values.spliterator(), true)
                .reduce((firstValue, secondValue) -> firstValue + secondValue)
                .orElse(0.0);

        return SimpleReport.of( "This is the mean value", sum / totalElements);
    }

}
