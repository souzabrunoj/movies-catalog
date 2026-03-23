package com.moviecatalog.core.navigator.step

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moviecatalog.core.designsystem.components.appbar.MovieStepTopAppBar
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.uimodel.UiMode

@Composable
internal fun MovieAppStepHost() {
    val flowNavigator = LocalFlowNavigator.current
    val navigator = LocalNavigator.currentOrThrow
    val step = (navigator.lastItem as StepBackedScreen).step
    val options = step.navigationOptions
    val reportedMode by flowNavigator.reportedStepUiMode.collectAsStateWithLifecycle()

    Column(Modifier.fillMaxSize()) {
        if (options.isVisible) {
            MovieStepTopAppBar(
                title = options.title,
                background = options.background,
                navigationAction = options.navigationAction,
                defaultBackEnabled = options.navigationAction == null &&
                    options.showNavigationAction &&
                    flowNavigator.canPop,
                onDefaultBackClick = { flowNavigator.pop() },
                navigationContentDescription = options.navigationContentDescription,
                primaryAction = options.primaryAction,
                secondaryAction = options.secondaryAction,
            )
        }
        Box(
            modifier = Modifier
                .weight(1f, fill = true)
                .fillMaxWidth(),
        ) {
            val contentAlpha = if (reportedMode is UiMode.Content) 1f else 0f
            Box(Modifier.fillMaxSize().alpha(contentAlpha)) {
                CurrentScreen()
            }
            StepSurfaceOverlay(reportedMode)
        }
    }
}

@Composable
private fun StepSurfaceOverlay(mode: UiMode) {
    val colors = MovieTheme.colors
    when (mode) {
        UiMode.Content -> Unit
        is UiMode.Loading -> {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(colors.backgroundBody.copy(alpha = 0.92f)),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp),
                ) {
                    CircularProgressIndicator(color = colors.contentBrand)
                    val message = mode.message
                    if (message != null) {
                        Spacer(Modifier.height(16.dp))
                        MovieText(
                            text = message,
                            variant = MovieTextVariant.TextMedium(),
                            contentColor = MovieTextColor.Medium,
                        )
                    }
                }
            }
        }
        is UiMode.Error -> {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(colors.backgroundBody.copy(alpha = 0.96f)),
                contentAlignment = Alignment.Center,
            ) {
                MovieText(
                    text = mode.message,
                    variant = MovieTextVariant.TextMedium(),
                    contentColor = MovieTextColor.High,
                    modifier = Modifier.padding(24.dp),
                )
            }
        }
    }
}
