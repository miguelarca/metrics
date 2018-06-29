package com.example.metrics.web.requests;

import javax.validation.constraints.NotNull;

public class AddValueDTO {
    @NotNull
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
