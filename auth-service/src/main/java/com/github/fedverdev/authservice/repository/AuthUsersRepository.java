package com.github.fedverdev.authservice.repository;

import com.github.fedverdev.authservice.model.db.AuthUsersTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUsersRepository extends JpaRepository<AuthUsersTable, String> {
    public boolean existsByUsername(String username);
    public Optional<AuthUsersTable> findByUsername(String username);
}
