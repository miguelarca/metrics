package com.example.metrics.statistics;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MaxTest {
    @Test
    public void itShouldReturnMaxValue() {
        final Sequence<Double> values = new ArrayListSequence<>();
        values.insert(50.0);
        values.insert(10.8);
        values.insert(600.766);
        values.insert(0.1);

        Statistic statistic = new Max();

        double value = statistic.getValue(values);

        assertEquals(600.766, value, 0);
    }

    @Test
    public void itShouldReturnTheRightReport() {
        final Sequence<Double> values = new ArrayListSequence<>();
        values.insert(50.0);
        values.insert(10.8);
        values.insert(600.766);
        values.insert(0.1);

        final StatisticReport report = SimpleReport.of("This is the maximum value", 600.766);

        Statistic statistic = new Max();

        assertEquals(report, statistic.getReport(values));
    }

}