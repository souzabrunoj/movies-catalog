package com.moviecatalog.features.login.signup.ui.uiModel

import com.moviecatalog.core.uimodel.UiMode
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
        setState(
            block = {
                when (
                    val result = registerUser(
                        usernameRaw = currentData.username,
                        password = currentData.password,
                        confirmPassword = currentData.confirmPassword,
                    )
                ) {
                    MovieRegisterUserResult.Success -> {
                        updateData {
                            copy(
                                formErrorMessage = null,
                                usernameErrorText = null,
                                username = "",
                                password = "",
                                confirmPassword = "",
                                passwordRules = evaluatePasswordRules("", ""),
                                feedbackEvent = MovieSignUpFeedbackEvent.Success(
                                    message = "Cadastro concluído com sucesso!",
                                ),
                            )
                        }
                    }

                    MovieRegisterUserResult.Failure.UsernameTaken -> {
                        val message = result.toFailureMessage()
                        updateData {
                            copy(
                                formErrorMessage = null,
                                usernameErrorText = message,
                                feedbackEvent = MovieSignUpFeedbackEvent.UsernameTaken(message),
                            )
                        }
                    }

                    is MovieRegisterUserResult.Failure -> {
                        updateData {
                            copy(
                                formErrorMessage = result.toFailureMessage(),
                                usernameErrorText = null,
                                feedbackEvent = null,
                            )
                        }
                    }
                }
            },
            loadingState = { UiMode.Loading() },
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
                "Este nome de usuário já está cadastrado."
        }
}
