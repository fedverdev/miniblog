package com.github.fedverdev.authservice.controllers.rest.auth;

import com.github.fedverdev.authservice.controllers.rest.auth.dto.RegistrationRequest;
import com.github.fedverdev.authservice.services.AuthUsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthRestController {
    private AuthUsersService authUsersService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        String username = authUsersService.attemptRegister(request).getUsername();
        return ResponseEntity.ok().body("User " + username + " registered successfully");
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-ID", authUsersService.validate().toString());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
