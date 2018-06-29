package com.example.metrics.statistics;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxTest {

    @Test
    public void itShouldReturnTheRightReport() {
        final TotalizingSequence<Double> values = new DoubleArrayListSequence();
        values.insert(50.0);
        values.insert(10.8);
        values.insert(600.766);
        values.insert(0.1);

        final StatisticReport report = SimpleReport.of("This is the maximum value", 600.766);

        Statistic statistic = new Max();

        assertEquals(report, statistic.getReport(values));
    }

}