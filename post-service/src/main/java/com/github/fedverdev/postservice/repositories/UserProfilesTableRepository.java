package com.github.fedverdev.postservice.repositories;

import com.github.fedverdev.postservice.model.db.UserProfilesTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfilesTableRepository extends JpaRepository<UserProfilesTable, UUID> {
}
