package com.github.fedverdev.postservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PostController {
    @PostMapping("/")
    public String createPost(@RequestHeader(value = "X-User-ID", required = true) String userId) {
        return "todo";
    }

    @GetMapping("/")
    public String getAllPosts(
            @RequestParam(value = "my_posts", defaultValue = "false", required = false) boolean myPosts,
            @RequestHeader(value = "X-User-ID", required = false) String userId
    ) {
        if (myPosts && userId != null) {
            return "todo";
        } else {
            return "todo";
        }
    }

    @PutMapping("/{id}")
    public String updatePost(@PathVariable UUID id, @RequestHeader(value = "X-User-ID", required = true) String userId) {
        return "todo";
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable UUID id, @RequestHeader(value = "X-User-ID", required = true) String userId) {
        return "todo";
    }
}
