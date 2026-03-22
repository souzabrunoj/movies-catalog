package com.moviecatalog.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moviecatalog.core.designsystem.components.image.MovieImage
import com.moviecatalog.core.designsystem.components.progress.MovieProgressBar
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.color.MovieSemanticColors
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.core.navigator.DestinationRegistry
import com.moviecatalog.core.navigator.LoginDestination
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.navigator.step.Step
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

private const val SPLASH_PROGRESS_MS = 2000
private const val SPLASH_HOLD_AFTER_PROGRESS_MS = 500L
private val LogoTileSize = 180.dp
private val LogoCornerDp = 40.dp
private val BottomLoadingPadding = 80.dp
private val ProgressBarWidth = 200.dp

private const val SPLASH_LOGO_COMPOSE_PATH =
    "https://souzabrunoj.github.io/movie-catalog-assets/splash/img_splash_logo.webp"

internal data object MovieCatalogSplashStep : Step() {

    @Composable
    override fun Content() {
        val navigator = LocalFlowNavigator.current
        val semantic = MovieTheme.colors
        var targetProgress by remember { mutableFloatStateOf(0f) }
        val animatedProgress by animateFloatAsState(
            targetValue = targetProgress,
            animationSpec = tween(durationMillis = SPLASH_PROGRESS_MS),
            label = "splashProgress",
        )

        LaunchedEffect(Unit) {
            delay(SPLASH_PROGRESS_MS + SPLASH_HOLD_AFTER_PROGRESS_MS)
            navigator.replaceAll(LoginDestination.Login)
        }

        Box(
            modifier = Modifier.fillMaxSize().background(splashVerticalGradient(semantic)),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier.size(LogoTileSize).clip(RoundedCornerShape(LogoCornerDp))
                        .background(semantic.backgroundSurface),
                    contentAlignment = Alignment.Center,
                ) {
                    MovieImage(
                        url = SPLASH_LOGO_COMPOSE_PATH,
                        modifier = Modifier.fillMaxSize(),
                    )
                    Box(
                        modifier = Modifier.matchParentSize().background(
                                Brush.radialGradient(
                                    colors = listOf(
                                        semantic.contentBrand.copy(alpha = 0.15f),
                                        Color.Transparent,
                                    ),
                                ),
                            ),
                    )
                }

                Spacer(Modifier.height(MovieSpace.XLarge2))

                MovieText(
                    text = "Cinegraph",
                    variant = MovieTextVariant.DisplayMedium(FontWeight.Bold),
                    contentColor = MovieTextColor.High,
                    textAlign = TextAlign.Center,
                )

                MovieText(
                    text = "YOUR CURATED CINEMATIC JOURNEY",
                    variant = MovieTextVariant.Overline(FontWeight.Medium),
                    contentColor = MovieTextColor.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = MovieSpace.XSmall),
                )
            }

            Column(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = BottomLoadingPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MovieText(
                    text = "CURATING CONTENT",
                    variant = MovieTextVariant.Overline(FontWeight.Bold),
                    contentColor = MovieTextColor.Medium,
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(MovieSpace.Medium))
                MovieProgressBar(
                    progress = animatedProgress,
                    modifier = Modifier.width(ProgressBarWidth),
                )
            }
        }
    }
}

@Composable
private fun splashVerticalGradient(semantic: MovieSemanticColors): Brush = Brush.verticalGradient(
    colors = listOf(
        semantic.backgroundBody,
        Color.Black,
        semantic.backgroundBrand.copy(alpha = 0.14f),
    ),
)
