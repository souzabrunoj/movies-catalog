package com.moviecatalog.core.designsystem.tokens.button

import androidx.compose.ui.graphics.Color
import com.moviecatalog.core.designsystem.tokens.color.MovieSemanticColors

internal data class MovieButtonColors(
    val background: Color,
    val content: Color,
)

internal object MovieButtonDefaults {
    fun colors(
        variant: MovieButtonVariant,
        semantic: MovieSemanticColors,
        enabled: Boolean,
        isLoading: Boolean,
    ): MovieButtonColors {
        val showAsDisabled = !enabled && !isLoading
        return when (variant) {
            MovieButtonVariant.Primary ->
                if (showAsDisabled) {
                    MovieButtonColors(
                        background = semantic.fillPrimaryDisabled,
                        content = semantic.contentDisabled,
                    )
                } else {
                    MovieButtonColors(
                        background = semantic.backgroundBrand,
                        content = semantic.contentOnBrandHigh,
                    )
                }
            MovieButtonVariant.Neutral ->
                if (showAsDisabled) {
                    MovieButtonColors(
                        background = semantic.fillNeutralDisabled,
                        content = semantic.contentDisabled,
                    )
                } else {
                    MovieButtonColors(
                        background = semantic.fillNeutral,
                        content = semantic.contentHigh,
                    )
                }
            MovieButtonVariant.Destructive ->
                if (showAsDisabled) {
                    MovieButtonColors(
                        background = semantic.fillDestructiveDisabled,
                        content = semantic.contentDisabled,
                    )
                } else {
                    MovieButtonColors(
                        background = semantic.fillDestructive,
                        content = semantic.contentOnSaturated,
                    )
                }
            MovieButtonVariant.Ghost ->
                if (showAsDisabled) {
                    MovieButtonColors(
                        background = Color.Transparent,
                        content = semantic.contentDisabled,
                    )
                } else {
                    MovieButtonColors(
                        background = Color.Transparent,
                        content = semantic.contentHigh,
                    )
                }
        }
    }
}
