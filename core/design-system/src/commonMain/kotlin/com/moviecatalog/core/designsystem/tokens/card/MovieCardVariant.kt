package com.moviecatalog.core.designsystem.tokens.card

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.moviecatalog.core.designsystem.tokens.size.MovieCornerRadius
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.size.MovieStrokeWidth

@Immutable
public data class MovieCardTokens(
    val posterBackground: Color,
    val posterBorder: Color,
    val posterHolderCorner: Dp,
    val posterImageCorner: Dp,
    val posterInset: Dp,
    val posterBorderWidth: Dp,
    val posterShadowElevation: Dp,
    val titleToPosterGap: Dp,
    val subtitleToTitleGap: Dp,
    val posterAspectRatio: Float,
)

public sealed class MovieCardVariant(public val tokens: MovieCardTokens) {

    public data object Default : MovieCardVariant(DefaultMovieCardTokens)
}

private val DefaultMovieCardTokens = MovieCardTokens(
    posterBackground = Color(0xFF244240),
    posterBorder = Color(0x4DFFFFFF),
    posterHolderCorner = MovieCornerRadius.XLarge,
    posterImageCorner = MovieCornerRadius.Medium,
    posterInset = MovieSpace.Medium,
    posterBorderWidth = MovieStrokeWidth.Hairline,
    posterShadowElevation = MovieSpace.XSmall2,
    titleToPosterGap = MovieSpace.Small,
    subtitleToTitleGap = MovieSpace.XSmall2,
    posterAspectRatio = 2f / 3f,
)
