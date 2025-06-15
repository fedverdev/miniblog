package com.github.fedverdev.services

import com.github.fedverdev.Profile
import com.github.fedverdev.models.UserProfile
import com.github.fedverdev.repositories.UserProfileRepository
import java.time.LocalDateTime
import java.util.UUID

class ProfileService(val userProfileRepository: UserProfileRepository) {
    fun registerProfile(request: Profile) {
        userProfileRepository.create(UserProfile(
            userId = UUID.fromString(request.userId),
            username = request.username,
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
        ))
        println("Profile registered successfully")
    }
}