package com.example.metrics.metrics;

import com.example.metrics.commands.AddMetricValue;
import com.example.metrics.commons.exceptions.ObjectNotFound;
import com.example.metrics.statistics.Statistic;
import com.example.metrics.statistics.StatisticReport;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class MetricsService {
    private final List<Statistic> statistics;
    private final Map<String, Metric> metrics;

    public MetricsService(List<Statistic> statistics) {
        this.statistics = statistics;
        this.metrics = new HashMap<>();
    }

    public NameableMetric createMetric(String metricName){
        Metric newMetric = new Metric(metricName);

        if(metrics.containsKey(newMetric.getId())){
            throw new MetricExists("There is a metric already with name: " + metricName);
        }

        metrics.put(newMetric.getId(), newMetric);

        return newMetric;
    }

    public NameableMetric getNameableMetric(String metricId) {
        return getMetric(metricId);
    }

    private Metric getMetric(String metricId) {
        if(!metrics.containsKey(metricId)){
            throw new ObjectNotFound("No metric with id: " + metricId);
        }

        return metrics.get(metricId);
    }

    public Collection<? extends NameableMetric> getMetrics() {
        return Collections.unmodifiableCollection(metrics.values());
    }

    public void addValue(@NotNull AddMetricValue command) {
        Assert.notNull(command, "An AddMetricValue command is required");

        Metric metric = getMetric(command.getMetricId());

        metric.addValue(command.getMetricValue());
    }

    public List<StatisticReport> getStatisticsFor(String metricId) {
        if(!metrics.containsKey(metricId)){
            throw new ObjectNotFound("No metric with id: " + metricId);
        }

        Metric metric = metrics.get(metricId);

        List<StatisticReport> reports = new ArrayList<>();

        for(Statistic statistic: statistics) {
            reports.add(metric.runReport(statistic));
        }

        return Collections.unmodifiableList(reports);
    }
}
