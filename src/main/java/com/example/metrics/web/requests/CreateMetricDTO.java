package com.example.metrics.web.requests;

import javax.validation.constraints.NotBlank;

public class CreateMetricDTO {
    @NotBlank
    private String metricName;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }
}
