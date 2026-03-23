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
        imageUrl = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier,
        posterAspectRatioOverride = MovieDetailConfigs.HeroImageAspectRatio,
    )
}
