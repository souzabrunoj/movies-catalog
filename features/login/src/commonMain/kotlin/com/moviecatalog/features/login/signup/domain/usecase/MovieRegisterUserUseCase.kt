package com.moviecatalog.features.login.signup.domain.usecase

import com.moviecatalog.features.login.signup.domain.model.MovieRegisterUserResult
import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository

internal class MovieRegisterUserUseCase(
    private val validateSignUpNonEmpty: MovieValidateSignUpNonEmptyUseCase,
    private val evaluatePasswordRules: MovieEvaluatePasswordRulesUseCase,
    private val checkUsernameAvailability: MovieCheckUsernameAvailabilityUseCase,
    private val repository: MovieSignUpRepository,
) {
    suspend operator fun invoke(
        usernameRaw: String,
        password: String,
        confirmPassword: String,
    ): MovieRegisterUserResult {
        validateSignUpNonEmpty(usernameRaw, password, confirmPassword)?.let { return it }

        if (password != confirmPassword) return MovieRegisterUserResult.Failure.PasswordMismatch

        if (!evaluatePasswordRules(password).allSatisfied) {
            return MovieRegisterUserResult.Failure.PasswordRulesNotMet
        }

        val normalized = usernameRaw.trim().lowercase()
        if (!checkUsernameAvailability(normalized)) {
            return MovieRegisterUserResult.Failure.UsernameTaken
        }

        repository.insertUser(normalized, password)
        return MovieRegisterUserResult.Success
    }
}
