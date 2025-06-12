package com.github.fedverdev.authservice.exceptions;

public class RegistrationFailedException extends RuntimeException {
    public RegistrationFailedException() {
        super("Registration failed");
    }
}
