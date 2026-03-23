package com.moviecatalog.features.login.signup.ui.uiModel

import com.moviecatalog.core.uimodel.UiModel
import com.moviecatalog.features.login.signup.domain.model.MovieRegisterUserResult
import com.moviecatalog.features.login.signup.domain.usecase.MovieEvaluatePasswordRulesUseCase
import com.moviecatalog.features.login.signup.domain.usecase.MovieRegisterUserUseCase
import com.moviecatalog.features.login.signup.ui.uiModel.state.MovieSignUpFeedbackEvent
import com.moviecatalog.features.login.signup.ui.uiModel.state.MovieSignUpUiState

internal class MovieSignUpUiModel(
    private val evaluatePasswordRules: MovieEvaluatePasswordRulesUseCase,
    private val registerUser: MovieRegisterUserUseCase,
) : UiModel<MovieSignUpUiState>(initialData = MovieSignUpUiState()) {

    fun onUsernameChange(value: String) {
        updateData {
            copy(username = value, formErrorMessage = null, usernameErrorText = null)
        }
    }

    fun onPasswordChange(value: String) {
        updateData {
            copy(
                password = value,
                passwordRules = evaluatePasswordRules(value, confirmPassword),
                formErrorMessage = null,
            )
        }
    }

    fun onConfirmPasswordChange(value: String) {
        updateData {
            copy(
                confirmPassword = value,
                passwordRules = evaluatePasswordRules(password, value),
                formErrorMessage = null,
            )
        }
    }

    fun togglePasswordVisible() {
        updateData {
            copy(passwordVisible = !passwordVisible)
        }
    }

    fun toggleConfirmPasswordVisible() {
        updateData {
            copy(confirmPasswordVisible = !confirmPasswordVisible)
        }
    }

    fun completeRegistration() {
        if (!currentData.passwordRules.allSatisfied || currentData.isSubmitting) return
        setState(
            block = registration@{
                updateData { copy(isSubmitting = true, formErrorMessage = null) }
                val result =
                    runCatching {
                        registerUser(
                            usernameRaw = currentData.username,
                            password = currentData.password,
                            confirmPassword = currentData.confirmPassword,
                        )
                    }.getOrElse { e ->
                        updateData {
                            copy(
                                isSubmitting = false,
                                formErrorMessage = e.message?.takeIf { it.isNotBlank() } ?: "Something went wrong.",
                            )
                        }
                        return@registration
                    }

                when (result) {
                    MovieRegisterUserResult.Success -> {
                        updateData {
                            copy(
                                isSubmitting = false,
                                formErrorMessage = null,
                                usernameErrorText = null,
                                username = "",
                                password = "",
                                confirmPassword = "",
                                passwordRules = evaluatePasswordRules("", ""),
                                feedbackEvent = MovieSignUpFeedbackEvent.Success(
                                    message = "Registration completed successfully.",
                                ),
                            )
                        }
                    }

                    MovieRegisterUserResult.Failure.UsernameTaken -> {
                        val message = result.toFailureMessage()
                        updateData {
                            copy(
                                isSubmitting = false,
                                formErrorMessage = null,
                                usernameErrorText = message,
                                feedbackEvent = MovieSignUpFeedbackEvent.UsernameTaken(message),
                            )
                        }
                    }

                    is MovieRegisterUserResult.Failure -> {
                        updateData {
                            copy(
                                isSubmitting = false,
                                formErrorMessage = result.toFailureMessage(),
                                usernameErrorText = null,
                                feedbackEvent = null,
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

    private fun MovieRegisterUserResult.toFailureMessage(): String =
        when (this) {
            is MovieRegisterUserResult.Failure -> toFailureDetailMessage()
            MovieRegisterUserResult.Success -> error("Success has no failure message")
        }

    private fun MovieRegisterUserResult.Failure.toFailureDetailMessage(): String =
        when (this) {
            MovieRegisterUserResult.Failure.EmptyUsername -> "Enter a username."
            MovieRegisterUserResult.Failure.EmptyPassword -> "Enter a password."
            MovieRegisterUserResult.Failure.EmptyConfirmPassword -> "Confirm your password."
            MovieRegisterUserResult.Failure.PasswordMismatch -> "Passwords do not match."
            MovieRegisterUserResult.Failure.PasswordRulesNotMet ->
                "Password must be at least 8 characters and include a letter, a number, and a special character."

            MovieRegisterUserResult.Failure.UsernameTaken ->
                "This username is already registered."
        }
}
