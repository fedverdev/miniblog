package com.github.fedverdev.authservice.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Entity
@Table(name = "auth_users")
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class AuthUsersTable {
    @Id
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
