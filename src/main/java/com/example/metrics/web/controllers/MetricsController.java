package com.example.metrics.web.controllers;

import com.example.metrics.commands.AddMetricValue;
import com.example.metrics.metrics.MetricsService;
import com.example.metrics.metrics.NameableMetric;
import com.example.metrics.statistics.StatisticReport;
import com.example.metrics.web.requests.AddValueDTO;
import com.example.metrics.web.requests.CreateMetricDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/metrics")
public class MetricsController {
    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping
    ResponseEntity<?> getMetrics() {
        Collection<? extends NameableMetric> metrics = metricsService.getMetrics();

        return ResponseEntity.ok(createNameableMetricResources(metrics));
    }

    @GetMapping("/{metricId}")
    ResponseEntity<?> getMetric(@PathVariable String metricId) {
        NameableMetric metric = metricsService.getNameableMetric(metricId);

        return ResponseEntity.ok(createMetricResource(metric));
    }

    @GetMapping("/{metricId}/statistics")
    ResponseEntity<?> getMetricStatistics(@PathVariable String metricId) {
        List<StatisticReport> statistics = metricsService.getStatisticsFor(metricId);

        return ResponseEntity.ok(createStatisticReportResources(statistics, metricId));
    }

    @PostMapping
    ResponseEntity<?> createMetric(@RequestBody CreateMetricDTO dto) {
        NameableMetric metric = metricsService.createMetric(dto.getMetricName());

        return ResponseEntity.ok(createMetricResource(metric));
    }

    @PostMapping("/{metricId}/values")
    ResponseEntity<?> addValue(@RequestBody AddValueDTO dto, @PathVariable String metricId) {
        AddMetricValue addMetricValue = new AddMetricValue(metricId, dto.getValue());

        metricsService.addValue(addMetricValue);

        return ResponseEntity.accepted().build();
    }

    private Resource<NameableMetric> createMetricResource(NameableMetric responseBody) {
        Resource<NameableMetric> resource = new Resource<>(responseBody);

        Link selfLink = linkTo(methodOn(MetricsController.class).getMetric(responseBody.getId())).withSelfRel();
        Link addValueLink = linkTo(methodOn(MetricsController.class)
                .addValue(null, responseBody.getId())).withRel("addValue");

        resource.add(selfLink, addValueLink);

        if(responseBody.isReportable()){
            Link statisticLink = linkTo(methodOn(MetricsController.class).getMetricStatistics(responseBody.getId())).withRel("statistics");
            resource.add(statisticLink);
        }

        return resource;
    }

    private Resources<List<StatisticReport>> createStatisticReportResources(List<StatisticReport> responseBody, String metricId) {

        Link selfRel = linkTo(methodOn(MetricsController.class).getMetricStatistics(metricId)).withSelfRel();

        Resources resources = new Resources<>(responseBody, selfRel);

        return resources;
    }

    private Resources<Collection<? extends NameableMetric>> createNameableMetricResources(Collection<? extends NameableMetric> responseBody) {

        Link metricsListLink = linkTo(methodOn(MetricsController.class).getMetrics()).withSelfRel();
        Link addMetricLink = linkTo(methodOn(MetricsController.class).createMetric(null)).withRel("create");

        Resources resources = new Resources<>(responseBody, metricsListLink, addMetricLink);

        return resources;
    }
}