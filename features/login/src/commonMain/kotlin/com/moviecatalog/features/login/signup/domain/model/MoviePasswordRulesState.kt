package com.moviecatalog.features.login.signup.domain.model

internal data class MoviePasswordRulesState(
    val hasMinLength: Boolean,
    val hasLetter: Boolean,
    val hasDigit: Boolean,
    val hasSpecialChar: Boolean,
    val hasPasswordsMatch: Boolean,
) {
    internal val allSatisfied: Boolean
        get() = hasMinLength &&
            hasLetter &&
            hasDigit &&
            hasSpecialChar &&
            hasPasswordsMatch
}
