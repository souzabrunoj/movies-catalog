package com.moviecatalog.core.designsystem.components.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.divider.MovieDividerOrientation
import com.moviecatalog.core.designsystem.tokens.divider.MovieDividerSize
import com.moviecatalog.core.designsystem.tokens.type.MovieColor

@Composable
public fun MovieDivider(
    modifier: Modifier = Modifier,
    orientation: MovieDividerOrientation = MovieDividerOrientation.Horizontal,
    size: MovieDividerSize = MovieDividerSize.Small,
    color: MovieColor = MovieColor.Brand,
) {
    val lineColor = color.resolve(MovieTheme.colors)
    when (orientation) {
        MovieDividerOrientation.Horizontal ->
            HorizontalDivider(
                modifier = modifier,
                thickness = size.dimensions.horizontalThickness,
                color = lineColor,
            )

        MovieDividerOrientation.Vertical ->
            VerticalDivider(
                modifier = modifier,
                thickness = size.dimensions.verticalThickness,
                color = lineColor,
            )
    }
}
