package com.moviecatalog.core.designsystem.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.moviecatalog.core.designsystem.components.image.MovieImage
import com.moviecatalog.core.designsystem.tokens.card.MovieCardVariant

@Composable
public fun MovieCard(
    imageUrl: String,
    contentDescription: String?,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    type: MovieCardVariant = MovieCardVariant.Default,
    posterAspectRatioOverride: Float? = null,
    content: @Composable ColumnScope.() -> Unit = {},
) {
    val t = type.tokens
    val posterAspectRatio = posterAspectRatioOverride ?: t.posterAspectRatio
    val posterShape = RoundedCornerShape(t.posterImageCorner)
    val holderShape = RoundedCornerShape(t.posterHolderCorner)

    Column(
        modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                },
            ),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .clip(holderShape)
                .background(t.posterBackground)
                .padding(t.posterInset),
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(posterAspectRatio)
                    .shadow(
                        elevation = t.posterShadowElevation,
                        shape = posterShape,
                        clip = false,
                        spotColor = Color.Black.copy(alpha = 0.28f),
                        ambientColor = Color.Black.copy(alpha = 0.12f),
                    ),
            ) {
                MovieImage(
                    url = imageUrl,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(posterShape)
                        .border(
                            width = t.posterBorderWidth,
                            color = t.posterBorder,
                            shape = posterShape,
                        ),
                )
            }
        }
        content()
    }
}
