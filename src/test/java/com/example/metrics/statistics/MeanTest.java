package com.example.metrics.statistics;

import org.junit.Test;

import static org.junit.Assert.*;

public class MeanTest {
    @Test
    public void itShouldReturnTheRightReport() {
        final MutableTotalizingSequence<Double> values = SequenceFactory.ofDouble();
        values.insert(50.0);
        values.insert(10.8);
        values.insert(600.766);
        values.insert(0.1);

        final StatisticReport report = SimpleReport.of("This is the mean value", 165.41649999999998);

        Statistic statistic = new Mean();

        assertEquals(report, statistic.getReport(values));
    }

}