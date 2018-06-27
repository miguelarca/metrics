package com.example.metrics.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MaxTest {
    @Test
    public void itShouldReturnMaxValue() {
        final double[] values = { 50.0, 10.8, 600.766, 0.1 };

        Statistic statistic = new Max();

        double value = statistic.getValue(values);

        assertEquals(600.766, value, 0);
    }

    @Test
    public void itShouldReturnTheRightReport() {
        final double[] values = { 50.0, 10.8, 600.766, 0.1 };
        final StatisticReport report = SimpleReport.of("This is the maximum value", 600.766);

        Statistic statistic = new Max();

        assertEquals(report, statistic.getReport(values));
    }

}