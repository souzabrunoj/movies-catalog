package com.moviecatalog.core.designsystem.components.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.SubcomposeAsyncImage
import com.moviecatalog.core.designsystem.components.button.MovieButtonSpinner
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant

@Composable
fun MovieImage(
    url: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    clipToBounds: Boolean = true,
    errorFeedbackText: String = "Unable to load image",
) {
    val semantic = MovieTheme.colors
    val model = url.takeIf { it.isNotBlank() }

    SubcomposeAsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        alignment = alignment,
        contentScale = contentScale,
        clipToBounds = clipToBounds,
        loading = { RemoteImageSpinnerPlaceholder(semantic.contentBrand) },
        error = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(semantic.fillNeutralDisabled),
                contentAlignment = Alignment.Center,
            ) {
                MovieText(
                    text = errorFeedbackText,
                    variant = MovieTextVariant.TextSmall(),
                    contentColor = MovieTextColor.Medium,
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = MovieSpace.Small),
                )
            }
        },
    )
}

@Composable
private fun RemoteImageSpinnerPlaceholder(spinnerColor: Color) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        MovieButtonSpinner(color = spinnerColor)
    }
}
