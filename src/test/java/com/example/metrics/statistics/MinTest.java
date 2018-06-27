package com.example.metrics.statistics;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinTest {
    @Test
    public void itShouldReturnMinValue() {
        final double[] values = { 50.0, 10.8, 600.766, 0.1 };

        Statistic statistic = new Min();

        double value = statistic.getValue(values);

        assertEquals(0.1, value, 0);
    }

    @Test
    public void itShouldReturnTheRightReport() {
        final double[] values = { 50.0, 10.8, 600.766, 0.1 };
        final StatisticReport report = SimpleReport.of("This is the minimum value", 0.1);

        Statistic statistic = new Min();

        assertEquals(report, statistic.getReport(values));
    }
}