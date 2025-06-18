package com.github.fedverdev.authservice.services;

import com.github.fedverdev.authservice.controllers.rest.auth.dto.RegistrationRequest;
import com.github.fedverdev.authservice.exceptions.RegistrationFailedException;
import com.github.fedverdev.authservice.exceptions.UsernameAlreadyExistsException;
import com.github.fedverdev.authservice.model.db.AuthUsersTable;
import com.github.fedverdev.authservice.repository.AuthUsersRepository;
import com.github.fedverdev.authservice.security.CustomUserDetails;
import com.github.fedverdev.authservice.services.clients.grpc.AuthServiceGrpcClient;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthUsersService {
    private final AuthUsersRepository authUsersRepository;
    private final AuthServiceGrpcClient authServiceGrpcClient;
    private final PasswordEncoder passwordEncoder;


    public AuthUsersTable registerAuth(String username, String rawPassword) throws UsernameAlreadyExistsException {
        if (authUsersRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException(username);
        }
        String hashedPassword = passwordEncoder.encode(rawPassword);
        return authUsersRepository.save(new AuthUsersTable(username, hashedPassword));
    }

    public void commitCompensation(AuthUsersTable authUsersTable) {
        authUsersRepository.delete(authUsersTable);
    }

    @Transactional
    public AuthUsersTable attemptRegister(RegistrationRequest request) throws RuntimeException {
        AuthUsersTable authUser = registerAuth(request.getUsername(), request.getPassword());
        var response = authServiceGrpcClient.attemptRegisterProfile(request.convertToGrpcMessage(authUser.getId().toString()));
        if (response == null || !response.getOk()) {
            commitCompensation(authUser);
            throw new RegistrationFailedException();
        } else {
            return authUser;
        }
    }

    public UUID validate() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    }
}
