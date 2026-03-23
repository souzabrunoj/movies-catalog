package com.moviecatalog.core.designsystem.tokens.divider

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.size.MovieStrokeWidth

@Immutable
public data class MovieDividerDimensionTokens(
    public val horizontalThickness: Dp,
    public val verticalThickness: Dp,
)

public sealed class MovieDividerSize(public val dimensions: MovieDividerDimensionTokens) {
    public data object Small : MovieDividerSize(SmallMovieDividerDimensions)

    public data object Medium : MovieDividerSize(MediumMovieDividerDimensions)

    public data object Large : MovieDividerSize(LargeMovieDividerDimensions)
}

private val SmallMovieDividerDimensions = MovieDividerDimensionTokens(
    horizontalThickness = MovieStrokeWidth.Hairline,
    verticalThickness = MovieStrokeWidth.Hairline,
)

private val MediumMovieDividerDimensions = MovieDividerDimensionTokens(
    horizontalThickness = MovieSpace.XSmall3,
    verticalThickness = MovieSpace.XSmall3,
)

private val LargeMovieDividerDimensions = MovieDividerDimensionTokens(
    horizontalThickness = MovieSpace.XSmall2,
    verticalThickness = MovieSpace.XSmall2,
)
