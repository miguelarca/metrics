package com.example.metrics.metrics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.Identifiable;

/**
 * A reportable metric exposes methods to get its name and if reports can be generated on its values
 *
 * @author Miguel.Mendez
 */
public interface ReportableMetric extends Identifiable<String> {
    String getName();
    @JsonIgnore
    boolean isReportable();
}
