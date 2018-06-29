package com.example.metrics.statistics;

/**
 * An statistic is a component that can generate statistic reports on the elements of the TotalizingSequence
 * The algorithm to get the report is left to the implementation
 *
 * @author Miguel.Mendez
 */
public interface Statistic {
    /**
     * Reurns statistic report on the elements of the TotalizingSequence
     * @param values sequence with the values to report on
     * @return statistic report
     */
    StatisticReport getReport(TotalizingSequence<Double> values);
}
