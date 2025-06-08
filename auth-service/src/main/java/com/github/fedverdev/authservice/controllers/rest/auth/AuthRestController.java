package com.github.fedverdev.authservice.controllers.rest.auth;

import com.github.fedverdev.authservice.controllers.rest.auth.dto.RegistrationRequest;
import com.github.fedverdev.authservice.services.AuthUsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthRestController {
    private AuthUsersService authUsersService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) throws BadRequestException {
        String username = request.getUsername();
        String password = request.getPassword();
        authUsersService.register(username, password);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate() throws BadRequestException {
        return ResponseEntity.ok().build();
    }
}
