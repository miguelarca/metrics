package com.example.metrics.statistics;

import org.springframework.stereotype.Component;

/**
 * It creates statistic report based on the maximum value contained in a TotalizingSequence of Doubles
 *
 * @author Miguel.Mendez
 */
@Component
class Max implements Statistic {
    private final static String message = "This is the maximum value";

    /**
     * @inheritDoc
     */
    private double getValue(TotalizingSequence<Double> values) {
        return values.getLast();
    }

    /**
     * @inheritDoc
     */
    @Override
    public StatisticReport getReport(TotalizingSequence<Double> values) {
        return SimpleReport.of(message, this.getValue(values));
    }
}
