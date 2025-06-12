package com.github.fedverdev.authservice.exceptions.handler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Map;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    Timestamp timestamp;
    Integer status;
    Map<String, String> errors;
    String error;
    String path;

    public ErrorResponse(Timestamp timestamp, Integer status, Map<String, String> errors, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
        this.path = path;
    }

    public ErrorResponse(Timestamp timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
