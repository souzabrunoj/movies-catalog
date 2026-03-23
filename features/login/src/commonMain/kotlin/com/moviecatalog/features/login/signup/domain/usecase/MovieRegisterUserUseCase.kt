package com.moviecatalog.features.login.signup.domain.usecase

import com.moviecatalog.features.login.signup.domain.model.MovieRegisterUserResult
import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository

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
        if (!repository.registerIfAbsent(normalized, password)) {
            return MovieRegisterUserResult.Failure.UsernameTaken
        }
        return MovieRegisterUserResult.Success
    }
}
