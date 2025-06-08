package com.github.fedverdev.authservice.services;

import com.github.fedverdev.authservice.exceptions.UsernameAlreadyExistsException;
import com.github.fedverdev.authservice.model.db.AuthUsersTable;
import com.github.fedverdev.authservice.repository.AuthUsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthUsersService {
    private final AuthUsersRepository authUsersRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthUsersTable register(String username, String rawPassword) throws UsernameAlreadyExistsException {
        if (authUsersRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException(username);
        }
        String hashedPassword = passwordEncoder.encode(rawPassword);
        return authUsersRepository.save(new AuthUsersTable(username, hashedPassword));
    }
}
