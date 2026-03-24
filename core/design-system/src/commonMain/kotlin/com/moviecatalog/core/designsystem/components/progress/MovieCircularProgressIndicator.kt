package com.moviecatalog.core.designsystem.components.progress

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
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.size.MovieComponentSize
import com.moviecatalog.core.designsystem.tokens.type.MovieColor

private const val ARC_SWEEP_DEGREES = 270f
private const val ARC_ROTATION_CYCLE_MS = 900

@Composable
public fun MovieCircularProgressIndicator(
    color: MovieColor = MovieColor.Brand,
    modifier: Modifier = Modifier,
) {
    val resolved = color.resolve(MovieTheme.colors)
    MovieArcSpinner(
        color = resolved,
        modifier = modifier,
        diameter = MovieComponentSize.CircularProgressIndicatorDiameter,
        strokeWidth = MovieComponentSize.CircularProgressIndicatorStrokeWidth,
    )
}

@Composable
internal fun MovieArcSpinner(
    color: Color,
    modifier: Modifier = Modifier,
    diameter: Dp,
    strokeWidth: Dp,
) {
    val transition = rememberInfiniteTransition(label = "movieArcSpinner")
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = ARC_ROTATION_CYCLE_MS, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "movieArcSpinnerRotation",
    )
    Canvas(
        modifier = modifier
            .size(diameter)
            .graphicsLayer { rotationZ = rotation },
    ) {
        val strokePx = strokeWidth.toPx()
        val dim = this.size.minDimension - strokePx
        val inset = strokePx / 2f
        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = ARC_SWEEP_DEGREES,
            useCenter = false,
            topLeft = Offset(inset, inset),
            size = Size(dim, dim),
            style = Stroke(width = strokePx, cap = StrokeCap.Round),
        )
    }
}
