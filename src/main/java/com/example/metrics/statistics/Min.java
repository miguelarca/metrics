package com.example.metrics.statistics;

import org.springframework.stereotype.Component;

/**
 * It creates statistic report based on the minimum value contained in a TotalizingSequence of Doubles
 *
 * @author Miguel.Mendez
 */
@Component
class Min implements Statistic {
    private final static String message = "This is the minimum value";

    private double getValue(TotalizingSequence<Double> values) {

        return values.getFirst();
    }

    /**
     * @inheritDoc
     */
    @Override
    public StatisticReport getReport(TotalizingSequence<Double> values) {
        return SimpleReport.of(message, this.getValue(values));
    }
}
