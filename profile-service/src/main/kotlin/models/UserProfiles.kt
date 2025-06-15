package com.github.fedverdev.models

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.util.UUID

object UserProfiles : Table("user_profile") {
    val userId = uuid("user_id")
    val username = varchar("username", 100)
    val firstName = varchar("first_name", 100)
    val lastName = varchar("last_name", 100)
    val email = varchar("email", 100)
    val insertedAt = datetime("inserted_at")
    val updatedAt = datetime("updated_at")
}

data class UserProfile(val userId: UUID, val username: String, val firstName: String, val lastName: String, val email: String)