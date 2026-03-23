package com.moviecatalog.core.designsystem.tokens.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class MovieSemanticColors(
    val contentHigh: Color,
    val contentMedium: Color,
    val contentLow: Color,
    val contentDisabled: Color,
    val contentBrand: Color,
    val contentOnBrandHigh: Color,
    val contentPositive: Color,
    val contentNegative: Color,
    val contentWarning: Color,
    val contentInfo: Color,
    val backgroundBody: Color,
    val backgroundSurface: Color,
    val backgroundBrand: Color,
    val fillNeutral: Color,
    val fillNeutralDisabled: Color,
    val fillDestructive: Color,
    val fillDestructiveDisabled: Color,
    val fillPrimaryDisabled: Color,
    val fillProgressTrack: Color,
    val contentOnSaturated: Color,
    val transparent: Color,
) {
    companion object {
        fun darkDefault(): MovieSemanticColors {
            val p = MovieColorPrimitives
            return MovieSemanticColors(
                contentHigh = Color(0xFFF5F5F5),
                contentMedium = Color(0xFFA3A3A3),
                contentLow = Color(0xFF737373),
                contentDisabled = Color(0xFF525252),
                contentBrand = p.Primary,
                contentOnBrandHigh = Color(0xFFFFFFFF),
                contentPositive = p.Secondary,
                contentNegative = p.Error,
                contentWarning = Color(0xFFFFB547),
                contentInfo = p.Tertiary,
                backgroundBody = p.Surface,
                backgroundSurface = p.SurfaceContainer,
                backgroundBrand = p.Primary,
                fillNeutral = p.NeutralFill,
                fillNeutralDisabled = p.NeutralFillDisabled,
                fillDestructive = p.DestructiveFill,
                fillDestructiveDisabled = p.DestructiveFillDisabled,
                fillPrimaryDisabled = p.BrandFillDisabled,
                fillProgressTrack = p.ProgressTrack,
                contentOnSaturated = p.InkOnSaturated,
                transparent = Color(0x00000000),
            )
        }
    }
}
