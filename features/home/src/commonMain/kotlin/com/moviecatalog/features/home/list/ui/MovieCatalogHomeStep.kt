package com.moviecatalog.features.home.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.moviecatalog.core.designsystem.components.card.MovieCard
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.tokens.card.MovieCardVariant
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.navigator.flow.state.collectDataAsState
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.core.navigator.step.StepNavigationOptions
import com.moviecatalog.features.home.details.ui.step.MovieCatalogDetailsStep
import com.moviecatalog.features.home.shared.ui.EmptyScreenContent
import org.koin.compose.viewmodel.koinViewModel

internal data object MovieCatalogHomeStep : Step() {

    override val navigationOptions: StepNavigationOptions
        @Composable
        get() = remember { StepNavigationOptions(title = "CINEGRAPH", showNavigationAction = false) }

    @Composable
    override fun Content() {
        val uiModel = koinViewModel<MovieCatalogHomeUiModel>()
        val data by uiModel.collectDataAsState()
        val flowNavigator = LocalFlowNavigator.current

        LaunchedEffect(key1 = Unit) { uiModel.getMovies() }

        if (data.items.isNotEmpty()) {
            MovieCatalogGrid(
                items = data.items,
                onMovieClick = { id -> flowNavigator.push(MovieCatalogDetailsStep(movieId = id)) },
            )
        } else {
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun MovieCatalogGrid(
    items: List<MovieObjectListItemUiModel>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(MovieSpace.Medium),
        horizontalArrangement = Arrangement.spacedBy(MovieSpace.Small),
        verticalArrangement = Arrangement.spacedBy(MovieSpace.XLarge),
    ) {
        items(items, key = { it.movieId }) { obj ->
            MovieCard(
                imageUrl = obj.posterImageUrl,
                contentDescription = obj.title,
                onClick = { onMovieClick(obj.movieId) },
            ) {
                val t = MovieCardVariant.Default.tokens
                Spacer(Modifier.height(t.titleToPosterGap))
                MovieText(
                    text = obj.title,
                    variant = MovieTextVariant.TextMedium(FontWeight.Bold),
                    contentColor = MovieTextColor.High,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(t.subtitleToTitleGap))
                MovieText(
                    text = obj.cardSubtitle,
                    variant = MovieTextVariant.TextSmall(),
                    contentColor = MovieTextColor.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
