package com.github.fedverdev.postservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {
    @GetMapping("/public/test")
    public String test() {
        return "it is a public test";
    }

    @GetMapping("/private/test")
    public String testPrivate(@RequestHeader("X-User-ID") String xUserId) {
        return "your user id is " + xUserId;
    }
}
