package com.moviecatalog.core.designsystem.tokens.type

import androidx.compose.ui.graphics.Color
import com.moviecatalog.core.designsystem.tokens.color.MovieSemanticColors

public enum class MovieColor {
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

    internal fun resolve(semantic: MovieSemanticColors): Color =
        when (this) {
            High -> semantic.contentHigh
            Medium -> semantic.contentMedium
            Low -> semantic.contentLow
            Disabled -> semantic.contentDisabled
            Brand -> semantic.contentBrand
            Info -> semantic.contentInfo
            Negative -> semantic.contentNegative
            Positive -> semantic.contentPositive
            Warning -> semantic.contentWarning
        }
}