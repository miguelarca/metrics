package com.example.metrics.web.controllers;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/")
public class MainController {
    @GetMapping
    ResponseEntity<?> entryPoint(){
        Map<String, Object> responseBody = new HashMap<>();

        responseBody.put("description", "web application which allows users to create multiple metric and post values to that metric");

        return ResponseEntity.ok(createResource(responseBody));
    }

    private Resource<Map<String, Object>> createResource(Map<String, Object> responseBody) {
        Resource<Map<String, Object>> resource = new Resource<>(responseBody);

        Link metricsListLink = linkTo(methodOn(MetricsController.class).getMetrics()).withRel("metrics");

        resource.add(metricsListLink);

        return resource;
    }
}
