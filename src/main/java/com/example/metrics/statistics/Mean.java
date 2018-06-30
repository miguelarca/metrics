package com.example.metrics.statistics;

import org.springframework.stereotype.Component;

/**
 * It creates statistic report based on the mean value contained in a TotalizingSequence of Doubles
 *
 * @author Miguel.Mendez
 */
@Component
class Mean implements Statistic {
    /**
     * @inheritDoc
     * @param values
     */
    @Override
    public StatisticReport getReport(TotalizingSequence<Double> values) {
        int totalElements = values.size();

        return SimpleReport.of( "This is the mean value", values.sum() / totalElements);
    }

}
