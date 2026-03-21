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
                        background = semantic.buttonPrimaryDisabledBackground,
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
                        background = semantic.buttonNeutralDisabledBackground,
                        content = semantic.contentDisabled,
                    )
                } else {
                    MovieButtonColors(
                        background = semantic.buttonNeutralEnabledBackground,
                        content = semantic.contentHigh,
                    )
                }
            MovieButtonVariant.Destructive ->
                if (showAsDisabled) {
                    MovieButtonColors(
                        background = semantic.buttonDestructiveDisabledBackground,
                        content = semantic.contentDisabled,
                    )
                } else {
                    MovieButtonColors(
                        background = semantic.buttonDestructiveEnabledBackground,
                        content = semantic.buttonDestructiveContent,
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
