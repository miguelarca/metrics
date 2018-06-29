package com.example.metrics.metrics;

import com.example.metrics.commands.AddMetricValue;
import com.example.metrics.commons.exceptions.ObjectNotFound;
import com.example.metrics.statistics.Statistic;
import com.example.metrics.statistics.StatisticReport;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * The metrics service encapsulates all of the operations that can be done on a metric. It holds a collection of metrics
 * by the user.
 *
 * It holds a collection of statistics which are components that will provide an statistic report of the values of a metric.
 * Those statistic are registered in the IOC container (Spring context) and get dependency injected when the bean is created.
 * It also exposes a method to register more statistics at runtime.
 *
 * @author Miguel.Mendez
 *
 */
@Service
public class MetricsService {
    private final List<Statistic> statistics;
    private final Map<String, Metric> metrics;

    public MetricsService(List<Statistic> statistics) {
        this.statistics = statistics;
        this.metrics = new HashMap<>();
    }

    public ReportableMetric createMetric(String metricName){
        Metric newMetric = new Metric(metricName);

        if(metrics.containsKey(newMetric.getId())){
            throw new MetricExists("There is a metric already with name: " + metricName);
        }

        metrics.put(newMetric.getId(), newMetric);

        return newMetric;
    }

    public void addStatistic(Statistic statistic) {
        this.statistics.add(statistic);
    }

    public void clearMetrics(){
        metrics.clear();
    }

    public ReportableMetric getNameableMetric(String metricId) {
        return getMetric(metricId);
    }

    private Metric getMetric(String metricId) {
        if(!metrics.containsKey(metricId)){
            throw new ObjectNotFound("No metric with id: " + metricId);
        }

        return metrics.get(metricId);
    }

    public Collection<ReportableMetric> getMetrics() {
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
