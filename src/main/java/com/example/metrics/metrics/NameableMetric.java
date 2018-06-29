package com.example.metrics.metrics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.Identifiable;

public interface NameableMetric extends Identifiable<String> {
    String getName();
    @JsonIgnore
    boolean isReportable();
}
