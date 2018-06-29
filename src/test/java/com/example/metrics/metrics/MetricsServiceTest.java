package com.example.metrics.metrics;

import com.example.metrics.commands.AddMetricValue;
import com.example.metrics.commons.exceptions.ObjectNotFound;
import com.example.metrics.statistics.Statistic;
import com.example.metrics.statistics.StatisticReport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MetricsServiceTest {
    @Mock
    private Statistic statistic;
    private MetricsService service;

    @Before
    public void setUp(){
        this.service = new MetricsService(Collections.singletonList(statistic));
    }

    @Test
    public void itShouldCreateMetric() {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        NameableMetric quickMetric = service.createMetric(metricName);

        assertEquals(metricName, quickMetric.getName());
        assertEquals(id, quickMetric.getId());
    }

    @Test(expected = MetricExists.class)
    public void itShouldNotCreateDuplicateMetric() {
        final String metricName = "Quick Metric";

        service.createMetric(metricName);
        service.createMetric(metricName);
    }

    @Test
    public void itShouldAddMetricValue(){
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        service.createMetric(metricName);
        AddMetricValue command = new AddMetricValue(id, 8.3);

        service.addValue(command);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldNotAddMetricValueNullCommand(){
        final String metricName = "Quick Metric";

        service.createMetric(metricName);

        service.addValue(null);
    }

    @Test(expected = ObjectNotFound.class)
    public void itShouldNotAddMetricValueToNonExistentMetric(){
        final String metricName = "Quick Metric";

        service.createMetric(metricName);

        AddMetricValue command = new AddMetricValue("fhkdf", 8.3);

        service.addValue(command);
    }

    @Test
    public void itShouldGetMetricsCollection() {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        service.createMetric(metricName);

        Collection<? extends NameableMetric> metrics = service.getMetrics();

        assertEquals(1, metrics.size());

        NameableMetric metric = metrics.iterator().next();

        assertEquals(id, metric.getId());
        assertEquals(metricName, metric.getName());
    }

    @Test
    public void itShouldGetStatisticReportsCollection() {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";
        final String reportName = "Test Report";
        final double reportValue = 0.0;

        when(statistic.getReport(any())).thenReturn(new StatisticReport() {
            @Override
            public String getMessage() {
                return reportName;
            }

            @Override
            public double getValue() {
                return reportValue;
            }
        });

        service = new MetricsService(Collections.singletonList(statistic));

        service.createMetric(metricName);

        for(Double value: Arrays.asList(2.3, 89.5, 47.2)){
            service.addValue(new AddMetricValue(id, value));
        }

        List<StatisticReport> statistics = service.getStatisticsFor(id);

        assertEquals(1, statistics.size());

    }

}