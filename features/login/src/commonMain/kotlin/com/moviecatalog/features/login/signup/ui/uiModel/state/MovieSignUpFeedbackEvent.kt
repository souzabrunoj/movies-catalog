package com.moviecatalog.features.login.signup.ui.uiModel.state

internal sealed class MovieSignUpFeedbackEvent {
    data class Success(val message: String) : MovieSignUpFeedbackEvent()
    data class UsernameTaken(val message: String) : MovieSignUpFeedbackEvent()
}