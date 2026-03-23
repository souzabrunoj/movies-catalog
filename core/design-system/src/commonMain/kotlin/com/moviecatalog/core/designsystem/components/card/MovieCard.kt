package com.moviecatalog.core.designsystem.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.moviecatalog.core.designsystem.components.image.MovieImage
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.tokens.card.MovieCardVariant
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant

@Composable
public fun MovieCard(
    title: String,
    subtitle: String,
    imageUrl: String,
    contentDescription: String?,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    type: MovieCardVariant = MovieCardVariant.Default,
    showMetadata: Boolean = true,
    posterAspectRatioOverride: Float? = null,
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
                        .matchParentSize()
                        .clip(posterShape)
                        .border(
                            width = t.posterBorderWidth,
                            color = t.posterBorder,
                            shape = posterShape,
                        ),
                )
            }
        }
        if (showMetadata) {
            Spacer(Modifier.height(t.titleToPosterGap))
            MovieText(
                text = title,
                variant = MovieTextVariant.TextMedium(FontWeight.Bold),
                contentColor = MovieTextColor.High,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(t.subtitleToTitleGap))
            MovieText(
                text = subtitle,
                variant = MovieTextVariant.TextSmall(),
                contentColor = MovieTextColor.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
