package com.github.fedverdev.authservice.exceptions.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Map;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ErrorResponse {
    Timestamp timestamp;
    Integer status;
    Map<String, String> errors;
    String path;
}
