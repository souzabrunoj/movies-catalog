package com.moviecatalog.features.home.ui.detail.componentes.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moviecatalog.core.designsystem.components.card.MovieCard
import com.moviecatalog.features.home.ui.detail.componentes.MovieDetailConfigs

@Composable
internal fun MovieDetailHeroSection(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    MovieCard(
        title = "",
        subtitle = "",
        imageUrl = imageUrl,
        contentDescription = contentDescription,
        onClick = {},
        modifier = modifier,
        showMetadata = false,
        posterAspectRatioOverride = MovieDetailConfigs.HeroImageAspectRatio,
    )
}
