package com.example.metrics.web.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String message;
    private String description;
    private int status;
    private List<ValidationError> validations;

    public ErrorResponse(String message, int status){
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ValidationError> getValidations() {
        return validations;
    }

    public void setValidations(List<ValidationError> validations) {
        this.validations = validations;
    }
}
