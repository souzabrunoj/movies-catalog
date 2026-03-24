package com.moviecatalog.features.home.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.features.home.details.ui.step.MovieCatalogDetailsStep
import com.moviecatalog.features.home.details.ui.uiModel.state.MovieObjectDetailUiModel

private val previewDetail = MovieObjectDetailUiModel(
    title = "Sample exhibition piece",
    heroImageUrl = "posters",
    artistDisplayName = "Studio Catalog",
    objectDate = "2024",
    dimensions = "120 × 80 cm",
    medium = "Digital composition",
    department = "Features",
    repository = "movie-catalog",
    creditLine = "Preview data",
)

@Preview(showBackground = true, name = "Details — filled")
@Composable
private fun MovieCatalogDetailsStepPreviewFilled() {
    MovieTheme {
        MovieCatalogDetailsStep(movieId = 1).StepContent(detail = previewDetail)
    }
}

@Preview(showBackground = true, name = "Details — minimal")
@Composable
private fun MovieCatalogDetailsStepPreviewMinimal() {
    MovieTheme {
        MovieCatalogDetailsStep(movieId = 2).StepContent(MovieObjectDetailUiModel())
    }
}
