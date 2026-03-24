package com.moviecatalog.features.login.auth.ui.uiModel

import com.moviecatalog.core.uimodel.UiModel
import com.moviecatalog.features.login.auth.domain.model.MovieLoginResult
import com.moviecatalog.features.login.auth.domain.usecase.MovieLoginUserUseCase
import com.moviecatalog.features.login.auth.ui.uiModel.state.MovieLoginFeedbackEvent
import com.moviecatalog.features.login.auth.ui.uiModel.state.MovieLoginUiState

internal class MovieLoginUiModel(
    private val loginUser: MovieLoginUserUseCase,
) : UiModel<MovieLoginUiState>(initialData = MovieLoginUiState()) {

    fun onUsernameChange(value: String) {
        updateData {
            copy(username = value, usernameErrorText = null, feedbackEvent = null)
        }
    }

    fun onPasswordChange(value: String) {
        updateData {
            copy(password = value, passwordErrorText = null, feedbackEvent = null)
        }
    }

    fun login() {
        if (currentData.isSubmitting) return
        setState(
            block = loginBlock@{
                updateData { copy(isSubmitting = true, usernameErrorText = null, passwordErrorText = null) }
                val result =
                    runCatching {
                        loginUser(currentData.username, currentData.password)
                    }.getOrElse { e ->
                        updateData {
                            copy(
                                isSubmitting = false,
                                feedbackEvent =
                                    MovieLoginFeedbackEvent.UnexpectedError(
                                        e.message?.takeIf { it.isNotBlank() } ?: UNEXPECTED_ERROR_MESSAGE,
                                    ),
                            )
                        }
                        return@loginBlock
                    }

                when (result) {
                    MovieLoginResult.Success -> {
                        updateData {
                            copy(
                                isSubmitting = false,
                                username = "",
                                password = "",
                                feedbackEvent = MovieLoginFeedbackEvent.NavigateHome,
                            )
                        }
                    }

                    MovieLoginResult.Failure.EmptyUsername -> {
                        updateData {
                            copy(
                                isSubmitting = false,
                                usernameErrorText = EMPTY_USERNAME_MESSAGE,
                            )
                        }
                    }

                    MovieLoginResult.Failure.EmptyPassword -> {
                        updateData {
                            copy(
                                isSubmitting = false,
                                passwordErrorText = EMPTY_PASSWORD_MESSAGE,
                            )
                        }
                    }

                    MovieLoginResult.Failure.UserNotFound -> {
                        val message = USER_NOT_FOUND_MESSAGE
                        updateData {
                            copy(
                                isSubmitting = false,
                                feedbackEvent = MovieLoginFeedbackEvent.UserNotFound(message),
                            )
                        }
                    }

                    MovieLoginResult.Failure.InvalidPassword -> {
                        val message = INVALID_PASSWORD_MESSAGE
                        updateData {
                            copy(
                                isSubmitting = false,
                                feedbackEvent = MovieLoginFeedbackEvent.InvalidPassword(message),
                            )
                        }
                    }
                }
            },
            loadingState = { null },
        )
    }

    fun consumeFeedback() {
        updateData { copy(feedbackEvent = null) }
    }

    private companion object {
        private const val UNEXPECTED_ERROR_MESSAGE: String = "Something went wrong."
        private const val EMPTY_USERNAME_MESSAGE: String = "Enter your username."
        private const val EMPTY_PASSWORD_MESSAGE: String = "Enter your password."
        private const val USER_NOT_FOUND_MESSAGE: String = "No account found for this username."
        private const val INVALID_PASSWORD_MESSAGE: String = "Incorrect password."
    }
}
