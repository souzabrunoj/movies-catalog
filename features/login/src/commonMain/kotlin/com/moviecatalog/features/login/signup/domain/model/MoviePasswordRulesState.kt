package com.moviecatalog.features.login.signup.domain.model

internal data class MoviePasswordRulesState(
    val hasMinLength: Boolean = false,
    val hasLetter: Boolean = false,
    val hasDigit: Boolean = false,
    val hasSpecialChar: Boolean = false,
    val hasPasswordsMatch: Boolean = false,
) {
    internal val allSatisfied: Boolean
        get() = hasMinLength &&
            hasLetter &&
            hasDigit &&
            hasSpecialChar &&
            hasPasswordsMatch
}
