package com.moviecatalog.core.designsystem.tokens.action

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace

sealed class MovieActionButtonGroupOverflowMode {
    data class Grid(
        val columns: Int,
        val horizontalSpacing: Dp = MovieSpace.XSmall,
        val verticalSpacing: Dp = MovieSpace.XSmall,
        val contentPadding: PaddingValues = PaddingValues(MovieSpace.None),
    ) : MovieActionButtonGroupOverflowMode()

    data class List(
        val verticalSpacing: Dp = MovieSpace.XSmall,
        val contentPadding: PaddingValues = PaddingValues(MovieSpace.None),
    ) : MovieActionButtonGroupOverflowMode()
}