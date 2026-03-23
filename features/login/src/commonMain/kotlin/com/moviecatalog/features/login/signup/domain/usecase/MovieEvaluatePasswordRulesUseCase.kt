package com.moviecatalog.features.login.signup.domain.usecase

import com.moviecatalog.features.login.signup.domain.model.MoviePasswordRulesState

internal class MovieEvaluatePasswordRulesUseCase {
    operator fun invoke(password: String): MoviePasswordRulesState =
        MoviePasswordRulesState(
            hasMinLength = password.length >= 8,
            hasLetter = password.any { it.isLetter() },
            hasDigit = password.any { it.isDigit() },
            hasSpecialChar = password.any { !it.isLetterOrDigit() },
        )
}
