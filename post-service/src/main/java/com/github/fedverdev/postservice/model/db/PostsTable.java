package com.github.fedverdev.postservice.model.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PostsTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "post_id")
    private UUID id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfilesTable user;

    @Column(name = "content")
    private String content;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Column(name = "inserted_at")
    private Timestamp insertedAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public PostsTable(UserProfilesTable user, String content, Boolean isPrivate) {
        this.user = user;
        this.content = content;
        this.isPrivate = isPrivate;
        this.insertedAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
