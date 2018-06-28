package com.example.metrics.commands;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Valid
public class AddMetricValue {
    @NotBlank
    private final String metricId;

    @NotNull
    private final Double metricValue;

    public AddMetricValue(String metricId, Double metricValue) {
        this.metricId = metricId;
        this.metricValue = metricValue;
    }

    public String getMetricId() {
        return metricId;
    }

    public Double getMetricValue() {
        return metricValue;
    }
}
