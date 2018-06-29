package com.example.metrics.metrics;

import com.example.metrics.statistics.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MetricTest {
    @Test
    public void itShouldBeCreated() {
        final String name = "My Metric";

        Metric metric = new Metric(name);

        assertEquals(name, metric.getName());

    }

    @Test
    public void itShouldReturnProperId() {
        final String name = "My Metric";
        final String id = "my-metric";

        Metric metric = new Metric(name);

        assertEquals(id, metric.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldNotBeCreatedWithNull() {
        Metric metric = new Metric(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldNotBeCreatedWithEmptyString() {
        Metric metric = new Metric("");
    }

    @Test
    public void itShouldRunReport() {
        final String name = "My Metric";
        final String reportName = "Test Report";
        final double reportValue = 0.0;

        Metric metric = new Metric(name);

        for(Double value: Arrays.asList(2.3, 89.5, 47.2)){
            metric.addValue(value);
        }

        StatisticReport statisticReport = metric.runReport(reportValues -> new StatisticReport() {
            @Override
            public String getMessage() {
                return reportName;
            }

            @Override
            public double getValue() {
                return reportValue;
            }
        });

        assertEquals(reportName, statisticReport.getMessage());
        assertEquals(reportValue, statisticReport.getValue(), 0);

    }
}