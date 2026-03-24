package com.moviecatalog.features.login.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.features.login.signup.domain.model.MoviePasswordRulesState
import com.moviecatalog.features.login.signup.ui.step.MovieCatalogSignUpStep
import com.moviecatalog.features.login.signup.ui.uiModel.state.MovieSignUpUiState

@Preview(showBackground = true, name = "Sign up — empty")
@Composable
private fun MovieCatalogSignUpStepPreviewEmpty() {
    MovieTheme {
        MovieCatalogSignUpStep.StepContent()
    }
}

@Preview(showBackground = true, name = "Sign up — ready to submit")
@Composable
private fun MovieCatalogSignUpStepPreviewReady() {
    MovieTheme {
        MovieCatalogSignUpStep.StepContent(
            data = MovieSignUpUiState(
                username = "new_user",
                password = "Secret42!",
                confirmPassword = "Secret42!",
                passwordRules = MoviePasswordRulesState(
                    hasMinLength = true,
                    hasLetter = true,
                    hasDigit = true,
                    hasSpecialChar = true,
                    hasPasswordsMatch = true,
                ),
            ),
        )
    }
}
