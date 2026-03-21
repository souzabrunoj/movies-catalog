package com.moviecatalog.core.designsystem.components.progress

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.IntOffset
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.progress.MovieProgressBarDefaults
import com.moviecatalog.core.designsystem.tokens.progress.MovieProgressBarTokens
import kotlin.math.roundToInt

@Composable
fun MovieProgressBar(
    modifier: Modifier = Modifier,
    progress: Float? = null,
) {
    val colors = MovieProgressBarDefaults.colors(MovieTheme.colors)
    val shape = RoundedCornerShape(MovieProgressBarTokens.CornerRadius)
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(MovieProgressBarTokens.Height)
            .clip(shape)
            .background(colors.track)
            .semantics {
                progressBarRangeInfo =
                    if (progress != null) {
                        ProgressBarRangeInfo(
                            current = progress.coerceIn(0f, 1f),
                            range = 0f..1f,
                        )
                    } else {
                        ProgressBarRangeInfo.Indeterminate
                    }
            },
    ) {
        if (progress != null) {
            val fraction = progress.coerceIn(0f, 1f)
            Box(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction)
                    .background(colors.fill),
            )
        } else {
            val infinite = rememberInfiniteTransition(label = "movieProgressBar")
            val shift by infinite.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = MovieProgressBarTokens.INDETERMINATE_CYCLE_MILLIS,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart,
                ),
                label = "shift",
            )
            val maxW = constraints.maxWidth.toFloat()
            val density = LocalDensity.current
            val segmentPx = maxW * MovieProgressBarTokens.INDETERMINATE_SEGMENT_FRACTION
            val travel = (maxW - segmentPx).coerceAtLeast(0f)
            val offsetX = travel * shift
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(with(density) { segmentPx.toDp() })
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .background(colors.fill),
            )
        }
    }
}
