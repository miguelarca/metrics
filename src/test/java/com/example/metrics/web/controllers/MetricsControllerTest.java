package com.example.metrics.web.controllers;

import com.example.metrics.commands.AddMetricValue;
import com.example.metrics.metrics.MetricsService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MetricsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MetricsService metricsService;

    @After
    public void tearDown(){
        metricsService.clearMetrics();
    }

    @Test
    public void shouldReturnBasicLinks() throws Exception {
        this.mockMvc.perform(get("/metrics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.create").exists());
    }

    @Test
    public void shouldCreateMetric() throws Exception {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        this.mockMvc.perform(
                    post("/metrics/").content("{\"metricName\": \"" + metricName + "\"}")
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value(metricName))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.addValue").exists());
    }

    @Test
    public void shouldCreateMoreThenOneMetric() throws Exception {
        final String metricName = "Second Metric";
        final String id = "second-metric";

        this.mockMvc.perform(
                post("/metrics").content("{\"metricName\": \"" + metricName + "\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value(metricName))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.addValue").exists());
    }

    @Test
    public void shouldNotCreateMetricWithoutBody() throws Exception {
        this.mockMvc.perform(
                post("/metrics"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreateMetricWithWrongContentType() throws Exception {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        this.mockMvc.perform(
                post("/metrics").content("{\"metricName\": \"" + metricName + "\"}")
                        .contentType(MediaType.APPLICATION_XHTML_XML)
        )
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void shouldNotCreateMetricWithNullName() throws Exception {
        this.mockMvc.perform(
                post("/metrics").content("{\"metricName\": null}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotCreateMetricWithEmptyName() throws Exception {
        this.mockMvc.perform(
                post("/metrics").content("{\"metricName\": \"\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnListOfMetrics() throws Exception {
        final String metricNames[] = { "Metric One", "Metric Two" };

        metricsService.createMetric(metricNames[0]);
        metricsService.createMetric(metricNames[1]);

        this.mockMvc.perform(
                get("/metrics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.create").exists())
                .andExpect(jsonPath("$._embedded.metricList").exists())
                .andExpect(jsonPath("$._embedded.metricList").isArray());
    }

    @Test
    public void shouldAddValueToMetric() throws Exception {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        metricsService.createMetric(metricName);

        this.mockMvc.perform(
                post("/metrics/" + id + "/values").content("{\"value\": 85.3}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotAddEmptyValueToMetric() throws Exception {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        metricsService.createMetric(metricName);

        this.mockMvc.perform(
                post("/metrics/" + id + "/values").content("{\"value\": null}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotAddNonNumericValueToMetric() throws Exception {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        metricsService.createMetric(metricName);

        this.mockMvc.perform(
                post("/metrics/" + id + "/values").content("{\"value\": \"foo\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotReturnListOfStatisticReportsForEmptyCollection() throws Exception {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        metricsService.createMetric(metricName);

        this.mockMvc.perform(
                get("/metrics/" + id + "/statistics"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnListOfStatisticReports() throws Exception {
        final String metricName = "Quick Metric";
        final String id = "quick-metric";

        metricsService.createMetric(metricName);
        metricsService.addValue(new AddMetricValue(id, 45.0));
        metricsService.addValue(new AddMetricValue(id, 4.340));
        metricsService.addValue(new AddMetricValue(id, 5.4535));
        metricsService.addValue(new AddMetricValue(id, 32.4365));

        this.mockMvc.perform(
                get("/metrics/" + id + "/statistics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("$._embedded.simpleReportList").exists())
                .andExpect(jsonPath("$._embedded.simpleReportList").isArray());
    }

}