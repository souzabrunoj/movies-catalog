package com.moviecatalog.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.moviecatalog.core.designsystem.tokens.color.MovieSemanticColors
import com.moviecatalog.core.designsystem.tokens.type.MovieTextStyle
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant

val LocalMovieSemanticColors =
    staticCompositionLocalOf<MovieSemanticColors> {
        error("MovieTheme not provided — wrap UI with MovieTheme { }")
    }

object MovieTheme {
    val colors: MovieSemanticColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMovieSemanticColors.current

    @Composable
    @ReadOnlyComposable
    fun textStyle(variant: MovieTextVariant): TextStyle =
        variant.resolve(LocalMovieSemanticColors.current)

    @Composable
    @ReadOnlyComposable
    fun textStyle(
        style: MovieTextStyle,
        fontWeight: FontWeight? = null,
        color: Color? = null,
    ): TextStyle =
        MovieTextVariant(style, fontWeight, color)
            .resolve(LocalMovieSemanticColors.current)
}

@Composable
fun MovieTheme(
    semanticColors: MovieSemanticColors = MovieSemanticColors.darkDefault(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalMovieSemanticColors provides semanticColors,
    ) {
        content()
    }
}