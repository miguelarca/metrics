package com.example.metrics.statistics;

import org.junit.Test;

import static org.junit.Assert.*;

public class MedianTest {

    @Test
    public void itShouldReturnTheRightReportFromOddLengthArray() {
        final TotalizingSequence<Double> values = new DoubleArrayListSequence();
        values.insert(9.0);
        values.insert(50.0);
        values.insert(10.8);
        values.insert(600.766);
        values.insert(0.1);

        final StatisticReport report = SimpleReport.of("This is the median value", 10.8);

        Statistic statistic = new Median();

        assertEquals(report, statistic.getReport(values));
    }


    @Test
    public void itShouldReturnTheRightReportFromEvenLengthArray() {
        final TotalizingSequence<Double> values = new DoubleArrayListSequence();
        values.insert(50.0);
        values.insert(10.8);
        values.insert(600.766);
        values.insert(0.1);

        final StatisticReport report = SimpleReport.of("This is the median value", 30.4);

        Statistic statistic = new Median();

        assertEquals(report, statistic.getReport(values));
    }

}