package com.github.fedverdev.postservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    @GetMapping("/test")
    public String test(@RequestHeader(value = "X-User-ID", required = false) String userId) {
        return "U currently logged in user: " + userId;
    }
}
