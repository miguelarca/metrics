package com.example.metrics.metrics;

import com.example.metrics.statistics.*;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * A Metric is a container that holds a collection of values. Those values can be used to run statistic reports
 *
 * @author Miguel.Mendez
 */
@Valid
final class Metric implements ReportableMetric {
    private final String name;
    private final TotalizingSequence<Double> values;

    Metric(@NotNull String name) {
        Assert.hasLength(name, "A metric requires a name");
        this.name = name;
        this.values = SequenceFactory.ofDouble();
    }

    @Override
    public String getName() {
        return this.name;
    }

    void addValue(Double value) {
        if(null != value){
            this.values.insert(value);
        }
    }

    @Override
    public boolean isReportable() {
        return this.values.size() > 0;
    }

    StatisticReport runReport(@NotNull Statistic statistic) {
        Assert.notNull(statistic, "A statistic implementation is needed to run a report");

        if(!isReportable()){
            throw new NotReportableMetric(this.name);
        }

        return statistic.getReport(this.values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metric metric = (Metric) o;

        return name.equals(metric.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String getId() {
        return this.name.toLowerCase().replace(" ", "-");
    }
}
