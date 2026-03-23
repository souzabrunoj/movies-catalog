package com.moviecatalog.features.login.auth.ui.uiModel.state

import com.moviecatalog.core.uimodel.UiModelState

internal data class MovieLoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameErrorText: String? = null,
    val passwordErrorText: String? = null,
    val feedbackEvent: MovieLoginFeedbackEvent? = null,
    val isSubmitting: Boolean = false,
) : UiModelState
