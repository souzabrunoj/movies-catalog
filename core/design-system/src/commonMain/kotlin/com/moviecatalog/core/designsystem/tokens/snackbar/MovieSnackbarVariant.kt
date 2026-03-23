package com.moviecatalog.core.designsystem.tokens.snackbar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moviecatalog.core.designsystem.icons.MovieIcons
import com.moviecatalog.core.designsystem.tokens.color.MovieSemanticColors


public enum class MovieSnackbarVariant {
    Success,
    Critical,
    Info,
    ;

    internal fun leadingIcon(): MovieIcons =
        when (this) {
            Success -> MovieIcons.CheckCircle
            Critical -> MovieIcons.Warning
            Info -> MovieIcons.Info
        }

    internal fun accentColor(semantic: MovieSemanticColors): Color =
        when (this) {
            Success -> semantic.contentPositive
            Critical -> semantic.contentNegative
            Info -> semantic.contentInfo
        }

    internal fun containerColor(semantic: MovieSemanticColors): Color =
        semantic.backgroundSurface

    internal companion object {
        val AccentBarWidth: Dp = 3.dp
        val LeadingIconCircleSize: Dp = 36.dp
        val LeadingIconGlyphSize: Dp = 20.dp
        val ContentHorizontalPadding: Dp = 14.dp
        val ContentVerticalPadding: Dp = 12.dp
    }
}
