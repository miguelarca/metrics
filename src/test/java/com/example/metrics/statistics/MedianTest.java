package com.example.metrics.statistics;

import org.junit.Test;

import static org.junit.Assert.*;

public class MedianTest {
    @Test
    public void itShouldReturnMedianValueFromOddLengthArray() {
        final double[] values = { 50.0, 10.8, 600.766, 0.1, 78.1 };

        Statistic statistic = new Median();

        double value = statistic.getValue(values);

        assertEquals(50.0, value, 0);
    }

    @Test
    public void itShouldReturnTheRightReportFromOddLengthArray() {
        final double[] values = { 50.0, 10.8, 600.766, 0.1, 78.1 };
        final StatisticReport report = SimpleReport.of("This is the median value", 50.0);

        Statistic statistic = new Median();

        assertEquals(report, statistic.getReport(values));
    }

    @Test
    public void itShouldReturnMedianValueFromEvenLengthArray() {
        final double[] values = { 50.0, 10.8, 600.766, 0.1 };

        Statistic statistic = new Median();

        double value = statistic.getValue(values);

        assertEquals(30.4, value, 0);
    }

    @Test
    public void itShouldReturnTheRightReportFromEvenLengthArray() {
        final double[] values = { 50.0, 10.8, 600.766, 0.1 };
        final StatisticReport report = SimpleReport.of("This is the median value", 30.4);

        Statistic statistic = new Median();

        assertEquals(report, statistic.getReport(values));
    }

}