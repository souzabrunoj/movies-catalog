package com.moviecatalog.features.login.signup.domain.usecase

import com.moviecatalog.features.login.signup.domain.model.MovieRegisterUserResult
import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository
import kotlinx.coroutines.delay

internal class MovieRegisterUserUseCase(
    private val validateSignUpNonEmpty: MovieValidateSignUpNonEmptyUseCase,
    private val evaluatePasswordRules: MovieEvaluatePasswordRulesUseCase,
    private val repository: MovieSignUpRepository,
) {
    suspend operator fun invoke(
        usernameRaw: String,
        password: String,
        confirmPassword: String,
    ): MovieRegisterUserResult {
        validateSignUpNonEmpty(usernameRaw, password, confirmPassword)?.let { return it }

        val passwordRules = evaluatePasswordRules(password, confirmPassword)
        if (!passwordRules.allSatisfied) {
            return if (!passwordRules.hasPasswordsMatch) {
                MovieRegisterUserResult.Failure.PasswordMismatch
            } else {
                MovieRegisterUserResult.Failure.PasswordRulesNotMet
            }
        }

        val normalized = usernameRaw.trim().lowercase()
        delay(SIMULATED_PERSISTENCE_DELAY_MS)
        if (!repository.registerIfAbsent(normalized, password)) {
            return MovieRegisterUserResult.Failure.UsernameTaken
        }
        return MovieRegisterUserResult.Success
    }

    private companion object {
        private const val SIMULATED_PERSISTENCE_DELAY_MS: Long = 1_500L
    }
}
