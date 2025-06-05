package com.github.fedverdev.postservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    @GetMapping("/test-public")
    public String test() {
        return "test";
    }

    @GetMapping("/test-private")
    public String testPrivate(@RequestHeader("X-User-ID") String xUserId) {
        return "your user id is " + xUserId;
    }
}
