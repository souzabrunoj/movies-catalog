package com.moviecatalog.features.login.signup.ui.uiModel.state

import com.moviecatalog.core.uimodel.UiModelState
import com.moviecatalog.features.login.signup.domain.model.MoviePasswordRulesState

internal data class MovieSignUpUiState(
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val passwordVisible: Boolean = false,
    val confirmPasswordVisible: Boolean = false,
    val passwordRules: MoviePasswordRulesState = MoviePasswordRulesState(
        hasMinLength = false,
        hasLetter = false,
        hasDigit = false,
        hasSpecialChar = false,
        hasPasswordsMatch = false,
    ),
    val formErrorMessage: String? = null,
    val usernameErrorText: String? = null,
    val feedbackEvent: MovieSignUpFeedbackEvent? = null,
) : UiModelState
