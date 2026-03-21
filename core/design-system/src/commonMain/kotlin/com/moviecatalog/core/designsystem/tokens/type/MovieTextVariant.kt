package com.moviecatalog.core.designsystem.tokens.type

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.moviecatalog.core.designsystem.tokens.color.MovieSemanticColors

@Immutable
data class MovieTextVariant(
    val textStyle: MovieTextStyle,
    val fontWeight: FontWeight? = null,
    val color: Color? = null,
) {
    internal fun resolve(colors: MovieSemanticColors): TextStyle {
        val token = textStyle
        return TextStyle(
            fontSize = token.fontSizeSp.sp,
            lineHeight = token.lineHeightSp.sp,
            fontWeight = fontWeight ?: token.defaultWeight,
            color = color ?: token.defaultColor(colors),
        )
    }

    internal fun resolve(
        colors: MovieSemanticColors,
        contentColor: MovieTextColor,
        textAlign: TextAlign = TextAlign.Start,
    ): TextStyle {
        val token = textStyle
        return TextStyle(
            fontSize = token.fontSizeSp.sp,
            lineHeight = token.lineHeightSp.sp,
            fontWeight = fontWeight ?: token.defaultWeight,
            color = color ?: contentColor.resolve(colors),
            textAlign = textAlign,
        )
    }

    companion object {
        fun DisplayMedium(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.DisplayMedium, fontWeight)

        fun DisplaySmall(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.DisplaySmall, fontWeight)

        fun HeadingLarge(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.HeadingLarge, fontWeight)

        fun HeadingMedium(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.HeadingMedium, fontWeight)

        fun HeadingSmall(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.HeadingSmall, fontWeight)

        fun HeadingXsmall(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.HeadingXsmall, fontWeight)

        fun HyperlinkLarge(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.HyperlinkLarge, fontWeight)

        fun HyperlinkMedium(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.HyperlinkMedium, fontWeight)

        fun HyperlinkSmall(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.HyperlinkSmall, fontWeight)

        fun Overline(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.Overline, fontWeight)

        fun TextLarge(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.TextLarge, fontWeight)

        fun TextMedium(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.TextMedium, fontWeight)

        fun TextSmall(fontWeight: FontWeight? = null) =
            MovieTextVariant(MovieTextStyle.TextSmall, fontWeight)
    }
}