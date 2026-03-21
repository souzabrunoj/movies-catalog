package com.moviecatalog.core.designsystem.tokens.type

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.moviecatalog.core.designsystem.tokens.color.MovieCatalogSemanticColors

@Immutable
data class MovieCatalogTextVariant(
    val textStyle: MovieCatalogTextStyle,
    val fontWeight: FontWeight? = null,
    val color: Color? = null,
) {
    fun resolve(colors: MovieCatalogSemanticColors): TextStyle {
        val token = textStyle
        return TextStyle(
            fontSize = token.fontSizeSp.sp,
            lineHeight = token.lineHeightSp.sp,
            fontWeight = fontWeight ?: token.defaultWeight,
            color = color ?: token.defaultColor(colors),
        )
    }
}
