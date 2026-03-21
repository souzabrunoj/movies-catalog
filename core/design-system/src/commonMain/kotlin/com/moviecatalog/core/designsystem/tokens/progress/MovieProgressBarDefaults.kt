package com.moviecatalog.core.designsystem.tokens.progress

import androidx.compose.ui.graphics.Color
import com.moviecatalog.core.designsystem.tokens.color.MovieSemanticColors

internal data class MovieProgressBarColors(
    val track: Color,
    val fill: Color,
)

internal object MovieProgressBarDefaults {
    fun colors(semantic: MovieSemanticColors): MovieProgressBarColors =
        MovieProgressBarColors(
            track = semantic.fillProgressTrack,
            fill = semantic.contentBrand,
        )
}
