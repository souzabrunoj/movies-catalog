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
            block = loginBlock@ {
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
                                        e.message?.takeIf { it.isNotBlank() } ?: "Something went wrong.",
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
                                usernameErrorText = "Enter your username.",
                            )
                        }
                    }

                    MovieLoginResult.Failure.EmptyPassword -> {
                        updateData {
                            copy(
                                isSubmitting = false,
                                passwordErrorText = "Enter your password.",
                            )
                        }
                    }

                    MovieLoginResult.Failure.UserNotFound -> {
                        val message = "No account found for this username."
                        updateData {
                            copy(
                                isSubmitting = false,
                                feedbackEvent = MovieLoginFeedbackEvent.UserNotFound(message),
                            )
                        }
                    }

                    MovieLoginResult.Failure.InvalidPassword -> {
                        val message = "Incorrect password."
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
}
