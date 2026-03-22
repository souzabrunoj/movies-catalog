package com.moviecatalog.features.home.ui.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.uimodel.flow.step.Step
import com.moviecatalog.features.home.data.MuseumObject
import com.moviecatalog.features.home.ui.EmptyScreenContent
import com.moviecatalog.features.home.ui.detail.MovieCatalogDetailsStep
import org.koin.compose.viewmodel.koinViewModel

internal data object MovieCatalogHomeStep : Step() {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ListViewModel>()
        val objects by viewModel.objects.collectAsStateWithLifecycle()
        val flowNavigator = LocalFlowNavigator.current

        AnimatedContent(objects.isNotEmpty()) { objectsAvailable ->
            if (objectsAvailable) {
                ObjectGrid(
                    objects = objects,
                    onObjectClick = { id -> flowNavigator.push(MovieCatalogDetailsStep(movieId = id)) },
                )
            } else {
                EmptyScreenContent(Modifier.fillMaxSize())
            }
        }
    }
}
@Composable
private fun ObjectGrid(
    objects: List<MuseumObject>,
    onObjectClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        modifier = modifier.fillMaxSize(),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues(),
    ) {
        items(objects, key = { it.objectID }) { obj ->
            ObjectFrame(
                obj = obj,
                onClick = { onObjectClick(obj.objectID) },
            )
        }
    }
}

@Composable
private fun ObjectFrame(
    obj: MuseumObject,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .padding(MovieSpace.XSmall)
            .clickable { onClick() },
    ) {
        AsyncImage(
            model = obj.primaryImageSmall,
            contentDescription = obj.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(MovieTheme.colors.backgroundSurface),
        )

        Spacer(Modifier.height(MovieSpace.XSmall3))

        MovieText(
            text = obj.title,
            variant = MovieTextVariant.TextMedium(FontWeight.Medium),
            contentColor = MovieTextColor.High,
        )
        MovieText(
            text = obj.artistDisplayName,
            variant = MovieTextVariant.TextMedium(),
            contentColor = MovieTextColor.Medium,
        )
        MovieText(
            text = obj.objectDate,
            variant = MovieTextVariant.TextSmall(),
            contentColor = MovieTextColor.Low,
        )
    }
}
