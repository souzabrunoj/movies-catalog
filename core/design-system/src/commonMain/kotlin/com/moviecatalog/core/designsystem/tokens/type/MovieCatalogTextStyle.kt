package com.moviecatalog.core.designsystem.tokens.type

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.moviecatalog.core.designsystem.tokens.color.MovieCatalogSemanticColors

enum class MovieCatalogTextStyle(
    internal val fontSizeSp: Float,
    internal val lineHeightSp: Float,
    internal val defaultWeight: FontWeight,
    internal val defaultColor: (MovieCatalogSemanticColors) -> Color,
) {
    DisplayMedium(
        fontSizeSp = 45f,
        lineHeightSp = 52f,
        defaultWeight = FontWeight.Normal,
        defaultColor = { it.contentHigh },
    ),
    DisplaySmall(
        fontSizeSp = 36f,
        lineHeightSp = 44f,
        defaultWeight = FontWeight.Normal,
        defaultColor = { it.contentHigh },
    ),
    HeadingLarge(
        fontSizeSp = 32f,
        lineHeightSp = 40f,
        defaultWeight = FontWeight.SemiBold,
        defaultColor = { it.contentHigh },
    ),
    HeadingMedium(
        fontSizeSp = 28f,
        lineHeightSp = 36f,
        defaultWeight = FontWeight.SemiBold,
        defaultColor = { it.contentHigh },
    ),
    HeadingSmall(
        fontSizeSp = 24f,
        lineHeightSp = 32f,
        defaultWeight = FontWeight.SemiBold,
        defaultColor = { it.contentHigh },
    ),
    HeadingXsmall(
        fontSizeSp = 20f,
        lineHeightSp = 28f,
        defaultWeight = FontWeight.SemiBold,
        defaultColor = { it.contentHigh },
    ),
    HyperlinkLarge(
        fontSizeSp = 18f,
        lineHeightSp = 24f,
        defaultWeight = FontWeight.Medium,
        defaultColor = { it.contentBrand },
    ),
    HyperlinkMedium(
        fontSizeSp = 16f,
        lineHeightSp = 24f,
        defaultWeight = FontWeight.Medium,
        defaultColor = { it.contentBrand },
    ),
    HyperlinkSmall(
        fontSizeSp = 14f,
        lineHeightSp = 20f,
        defaultWeight = FontWeight.Medium,
        defaultColor = { it.contentBrand },
    ),
    Overline(
        fontSizeSp = 12f,
        lineHeightSp = 16f,
        defaultWeight = FontWeight.Medium,
        defaultColor = { it.contentLow },
    ),
    TextLarge(
        fontSizeSp = 18f,
        lineHeightSp = 24f,
        defaultWeight = FontWeight.Normal,
        defaultColor = { it.contentHigh },
    ),
    TextMedium(
        fontSizeSp = 16f,
        lineHeightSp = 24f,
        defaultWeight = FontWeight.Normal,
        defaultColor = { it.contentMedium },
    ),
    TextSmall(
        fontSizeSp = 14f,
        lineHeightSp = 20f,
        defaultWeight = FontWeight.Normal,
        defaultColor = { it.contentLow },
    ),
}
