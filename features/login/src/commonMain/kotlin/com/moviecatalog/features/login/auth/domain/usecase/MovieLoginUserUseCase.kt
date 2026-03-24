package com.moviecatalog.features.login.auth.domain.usecase

import com.moviecatalog.features.login.auth.domain.model.MovieLoginResult
import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository
import com.moviecatalog.features.login.signup.domain.repository.UserCredentialVerification
import kotlinx.coroutines.delay

internal class MovieLoginUserUseCase(
    private val repository: MovieSignUpRepository,
) {
    suspend operator fun invoke(usernameRaw: String, password: String): MovieLoginResult {
        val username = usernameRaw.trim()
        if (username.isEmpty()) return MovieLoginResult.Failure.EmptyUsername
        if (password.isEmpty()) return MovieLoginResult.Failure.EmptyPassword

        val normalized = username.lowercase()
        delay(SIMULATED_LOGIN_VALIDATION_DELAY_MS)
        return when (repository.verifyCredentials(normalized, password)) {
            UserCredentialVerification.Match -> MovieLoginResult.Success
            UserCredentialVerification.UnknownUser -> MovieLoginResult.Failure.UserNotFound
            UserCredentialVerification.WrongPassword -> MovieLoginResult.Failure.InvalidPassword
        }
    }

    private companion object {
        private const val SIMULATED_LOGIN_VALIDATION_DELAY_MS: Long = 1_500L
    }
}
