package com.moviecatalog.features.login.signup.domain.usecase

import com.moviecatalog.features.login.signup.domain.model.MovieRegisterUserResult

internal class MovieValidateSignUpNonEmptyUseCase {
    operator fun invoke(
        usernameRaw: String,
        password: String,
        confirmPassword: String,
    ): MovieRegisterUserResult.Failure? {
        if (usernameRaw.isBlank()) return MovieRegisterUserResult.Failure.EmptyUsername
        if (password.isEmpty()) return MovieRegisterUserResult.Failure.EmptyPassword
        if (confirmPassword.isEmpty()) return MovieRegisterUserResult.Failure.EmptyConfirmPassword
        return null
    }
}
