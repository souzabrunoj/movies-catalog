package com.moviecatalog.core.designsystem.components.button

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import com.moviecatalog.core.designsystem.tokens.size.MovieComponentSize


@Composable
internal fun MovieButtonSpinner(
    color: Color,
    modifier: Modifier = Modifier,
    size: Dp = MovieComponentSize.ButtonSpinnerDiameter,
    strokeWidth: Dp = MovieComponentSize.ButtonSpinnerStrokeWidth,
    sweepDegrees: Float = 270f,
) {
    val transition = rememberInfiniteTransition()
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )
    Canvas(
        modifier = modifier
            .size(size)
            .graphicsLayer { rotationZ = rotation },
    ) {
        val strokePx = strokeWidth.toPx()
        val dim = this.size.minDimension - strokePx
        val inset = strokePx / 2f
        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = sweepDegrees,
            useCenter = false,
            topLeft = Offset(inset, inset),
            size = Size(dim, dim),
            style = Stroke(width = strokePx, cap = StrokeCap.Round),
        )
    }
}
