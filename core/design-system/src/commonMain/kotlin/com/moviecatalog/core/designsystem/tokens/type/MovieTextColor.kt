package com.moviecatalog.core.designsystem.tokens.type

import androidx.compose.ui.graphics.Color
import com.moviecatalog.core.designsystem.tokens.color.MovieSemanticColors

enum class MovieTextColor {
    High,
    Medium,
    Low,
    Disabled,
    Brand,
    Info,
    Negative,
    Positive,
    Warning,
    ;

    fun resolve(colors: MovieSemanticColors): Color =
        when (this) {
            High -> colors.contentHigh
            Medium -> colors.contentMedium
            Low -> colors.contentLow
            Disabled -> colors.contentDisabled
            Brand -> colors.contentBrand
            Info -> colors.contentInfo
            Negative -> colors.contentNegative
            Positive -> colors.contentPositive
            Warning -> colors.contentWarning
        }
}