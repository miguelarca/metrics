package com.example.metrics.statistics;

import org.springframework.stereotype.Component;

/**
 * It creates statistic report based on the arithmetic median value contained in a TotalizingSequence of Doubles
 *
 * @author Miguel.Mendez
 */
@Component
class Median implements Statistic {
    private final static String message = "This is the median value";

    private double getValue(TotalizingSequence<Double> values) {

        if (values.size() % 2 != 0){
            return values.getElement(values.size() / 2);
        }

        return (values.getElement((values.size() - 1) / 2) + values.getElement(values.size()  / 2)) / 2.0;

    }

    /**
     * @inheritDoc
     * @param values
     */
    @Override
    public StatisticReport getReport(TotalizingSequence<Double> values) {
        return SimpleReport.of(message, this.getValue(values));
    }

}
