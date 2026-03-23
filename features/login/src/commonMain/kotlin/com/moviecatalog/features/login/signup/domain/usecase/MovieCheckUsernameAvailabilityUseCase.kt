package com.moviecatalog.features.login.signup.domain.usecase

import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository

internal class MovieCheckUsernameAvailabilityUseCase(
    private val repository: MovieSignUpRepository,
) {
    suspend operator fun invoke(normalizedUsername: String): Boolean =
        repository.countUsersWithUsername(normalizedUsername) == 0L
}
