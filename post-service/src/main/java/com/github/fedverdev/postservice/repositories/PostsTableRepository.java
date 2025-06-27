package com.github.fedverdev.postservice.repositories;

import com.github.fedverdev.postservice.model.db.PostsTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostsTableRepository extends JpaRepository<PostsTable, UUID> {
}