package com.moviecatalog.core.designsystem.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.moviecatalog.core.designsystem.components.progress.MovieArcSpinner
import com.moviecatalog.core.designsystem.tokens.size.MovieComponentSize

@Composable
internal fun MovieButtonSpinner(
    color: Color,
    modifier: Modifier = Modifier,
) {
    MovieArcSpinner(
        color = color,
        modifier = modifier,
        diameter = MovieComponentSize.ButtonSpinnerDiameter,
        strokeWidth = MovieComponentSize.ButtonSpinnerStrokeWidth,
    )
}
