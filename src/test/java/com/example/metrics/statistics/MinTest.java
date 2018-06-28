package com.example.metrics.statistics;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinTest {

    @Test
    public void itShouldReturnTheRightReport() {
        final TotalizingSequence<Double> values = new ArrayListSequence();
        values.insert(50.0);
        values.insert(10.8);
        values.insert(600.766);
        values.insert(0.1);

        final StatisticReport report = SimpleReport.of("This is the minimum value", 0.1);

        Statistic statistic = new Min();

        assertEquals(report, statistic.getReport(values));
    }
}