package com.moviecatalog.core.designsystem.tokens.input

import androidx.compose.ui.unit.Dp
import com.moviecatalog.core.designsystem.tokens.size.MovieComponentSize
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace

internal object MovieTextInputTokens {
    val wrapperVerticalSpacing: Dp = MovieSpace.XSmall2
    val fieldHorizontalPadding: Dp = MovieSpace.Small
    val fieldVerticalPadding: Dp = MovieSpace.XSmall
    val leadingGap: Dp = MovieSpace.XSmall2
    val trailingGap: Dp = MovieSpace.XSmall2
    val labelTrailingGap: Dp = MovieSpace.XSmall2
    val errorIndicatorDiameter: Dp = MovieSpace.Large
    const val OPTIONAL_INDICATOR: String = "(optional)"

    fun minFieldHeight(size: MovieTextInputSize): Dp =
        when (size) {
            MovieTextInputSize.Medium -> MovieComponentSize.MinTouchTarget
        }
}