package com.moviecatalog.features.home.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.features.home.list.ui.step.MovieCatalogHomeStep
import com.moviecatalog.features.home.list.ui.uiModel.state.MovieObjectListItemUiModel

private val previewItems = listOf(
    MovieObjectListItemUiModel(
        movieId = 1,
        title = "Night at the Museum",
        cardSubtitle = "2006 · Comedy",
        posterImageUrl = "image_url_placeholder",
    ),
    MovieObjectListItemUiModel(
        movieId = 2,
        title = "The Grand Budapest Hotel",
        cardSubtitle = "2014 · Comedy",
        posterImageUrl = "image_url_placeholder",
    ),
    MovieObjectListItemUiModel(
        movieId = 3,
        title = "Very Long Title That Should Ellipsis Nicely in the Grid",
        cardSubtitle = "Subtitle that also wraps or truncates depending on space",
        posterImageUrl = "image_url_placeholder",
    ),
)

@Preview(showBackground = true, name = "Home — empty grid")
@Composable
private fun MovieCatalogHomeStepPreviewEmpty() {
    MovieTheme {
        MovieCatalogHomeStep.StepContent()
    }
}

@Preview(showBackground = true, name = "Home — with items")
@Composable
private fun MovieCatalogHomeStepPreviewWithItems() {
    MovieTheme {
        MovieCatalogHomeStep.StepContent(items = previewItems)
    }
}
