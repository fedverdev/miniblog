package com.github.fedverdev.authservice.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("Username |" + username + "| already exists");
    }
}
