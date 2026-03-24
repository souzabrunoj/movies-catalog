package com.moviecatalog.features.login.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.features.login.auth.ui.step.MovieCatalogLoginStep
import com.moviecatalog.features.login.auth.ui.uiModel.state.MovieLoginUiState

@Preview(showBackground = true, name = "Login — empty")
@Composable
private fun MovieCatalogLoginStepPreviewEmpty() {
    MovieTheme {
        MovieCatalogLoginStep.StepContent()
    }
}

@Preview(showBackground = true, name = "Login — filled")
@Composable
private fun MovieCatalogLoginStepPreviewFilled() {
    MovieTheme {
        MovieCatalogLoginStep.StepContent(
            data = MovieLoginUiState(
                username = "movie_fan",
                password = "secret42",
            ),
        )
    }
}

@Preview(showBackground = true, name = "Login — submitting")
@Composable
private fun MovieCatalogLoginStepPreviewSubmitting() {
    MovieTheme {
        MovieCatalogLoginStep.StepContent(
            data = MovieLoginUiState(
                username = "movie_fan",
                password = "secret42",
                isSubmitting = true,
            ),
        )
    }
}
