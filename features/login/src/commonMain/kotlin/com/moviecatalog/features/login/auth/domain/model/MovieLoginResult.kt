package com.moviecatalog.features.login.auth.domain.model

internal sealed class MovieLoginResult {
    data object Success : MovieLoginResult()

    sealed class Failure : MovieLoginResult() {
        data object EmptyUsername : Failure()
        data object EmptyPassword : Failure()
        data object UserNotFound : Failure()
        data object InvalidPassword : Failure()
    }
}
