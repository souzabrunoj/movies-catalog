package com.moviecatalog.features.login.auth.ui.uiModel.state

internal sealed class MovieLoginFeedbackEvent {
    data class UserNotFound(val message: String) : MovieLoginFeedbackEvent()

    data class InvalidPassword(val message: String) : MovieLoginFeedbackEvent()

    data class UnexpectedError(val message: String) : MovieLoginFeedbackEvent()

    data object NavigateHome : MovieLoginFeedbackEvent()
}
