package com.moviecatalog.features.login.signup.domain.model

internal sealed class MovieRegisterUserResult {
    data object Success : MovieRegisterUserResult()

    sealed class Failure : MovieRegisterUserResult() {
        data object EmptyUsername : Failure()
        data object EmptyPassword : Failure()
        data object EmptyConfirmPassword : Failure()
        data object PasswordMismatch : Failure()
        data object PasswordRulesNotMet : Failure()
        data object UsernameTaken : Failure()
    }
}
