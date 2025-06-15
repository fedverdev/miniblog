package com.github.fedverdev.repositories

import com.github.fedverdev.models.UserProfile
import com.github.fedverdev.models.UserProfiles
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class UserProfileRepository {
    fun create(userProfile: UserProfile): UserProfile = transaction {
        UserProfiles.insert {
            it[userId] = userProfile.userId
            it[username] = userProfile.username
            it[firstName] = userProfile.firstName
            it[lastName] = userProfile.lastName
            it[email] = userProfile.email
            it[insertedAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }
        userProfile
    }
}