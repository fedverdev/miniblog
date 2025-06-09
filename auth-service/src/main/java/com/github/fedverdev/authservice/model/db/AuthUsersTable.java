package com.github.fedverdev.authservice.model.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "auth_users")
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class AuthUsersTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "inserted_at")
    private Timestamp insertedAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public AuthUsersTable(String username, String password) {
        this.username = username;
        this.password = password;
        this.insertedAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
