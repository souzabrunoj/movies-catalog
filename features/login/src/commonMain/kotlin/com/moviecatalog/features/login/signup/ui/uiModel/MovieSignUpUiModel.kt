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
                                formErrorMessage = e.message?.takeIf { it.isNotBlank() } ?: UNEXPECTED_ERROR_MESSAGE,
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
                                    message = REGISTRATION_SUCCESS_MESSAGE,
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
            MovieRegisterUserResult.Success -> error(SUCCESS_HAS_NO_FAILURE_MESSAGE)
        }

    private fun MovieRegisterUserResult.Failure.toFailureDetailMessage(): String =
        when (this) {
            MovieRegisterUserResult.Failure.EmptyUsername -> EMPTY_USERNAME_MESSAGE
            MovieRegisterUserResult.Failure.EmptyPassword -> EMPTY_PASSWORD_MESSAGE
            MovieRegisterUserResult.Failure.EmptyConfirmPassword -> EMPTY_CONFIRM_PASSWORD_MESSAGE
            MovieRegisterUserResult.Failure.PasswordMismatch -> PASSWORD_MISMATCH_MESSAGE
            MovieRegisterUserResult.Failure.PasswordRulesNotMet -> PASSWORD_RULES_NOT_MET_MESSAGE
            MovieRegisterUserResult.Failure.UsernameTaken -> USERNAME_TAKEN_MESSAGE
        }

    private companion object {
        private const val UNEXPECTED_ERROR_MESSAGE: String = "Something went wrong."
        private const val REGISTRATION_SUCCESS_MESSAGE: String = "Registration completed successfully."
        private const val SUCCESS_HAS_NO_FAILURE_MESSAGE: String = "Success has no failure message"
        private const val EMPTY_USERNAME_MESSAGE: String = "Enter a username."
        private const val EMPTY_PASSWORD_MESSAGE: String = "Enter a password."
        private const val EMPTY_CONFIRM_PASSWORD_MESSAGE: String = "Confirm your password."
        private const val PASSWORD_MISMATCH_MESSAGE: String = "Passwords do not match."
        private const val PASSWORD_RULES_NOT_MET_MESSAGE: String =
            "Password must be at least 8 characters and include a letter, a number, and a special character."
        private const val USERNAME_TAKEN_MESSAGE: String = "This username is already registered."
    }
}
