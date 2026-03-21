package com.moviecatalog.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.moviecatalog.core.designsystem.tokens.color.MovieCatalogSemanticColors
import com.moviecatalog.core.designsystem.tokens.type.MovieCatalogTextStyle
import com.moviecatalog.core.designsystem.tokens.type.MovieCatalogTextVariant

val LocalMovieCatalogSemanticColors =
    staticCompositionLocalOf<MovieCatalogSemanticColors> {
        error("MovieCatalogTheme not provided — wrap UI with MovieCatalogTheme { }")
    }

object MovieCatalogTheme {
    val colors: MovieCatalogSemanticColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMovieCatalogSemanticColors.current

    @Composable
    @ReadOnlyComposable
    fun textStyle(variant: MovieCatalogTextVariant): TextStyle =
        variant.resolve(LocalMovieCatalogSemanticColors.current)


    @Composable
    @ReadOnlyComposable
    fun textStyle(
        style: MovieCatalogTextStyle,
        fontWeight: FontWeight? = null,
        color: Color? = null,
    ): TextStyle =
        MovieCatalogTextVariant(style, fontWeight, color)
            .resolve(LocalMovieCatalogSemanticColors.current)
}

@Composable
fun MovieCatalogTheme(
    semanticColors: MovieCatalogSemanticColors = MovieCatalogSemanticColors.darkDefault(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalMovieCatalogSemanticColors provides semanticColors,
    ) {
        content()
    }
}
