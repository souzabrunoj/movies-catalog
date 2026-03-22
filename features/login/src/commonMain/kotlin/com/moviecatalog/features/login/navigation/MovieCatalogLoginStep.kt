package com.moviecatalog.features.login.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moviecatalog.core.designsystem.components.button.MovieButton
import com.moviecatalog.core.designsystem.tokens.button.MovieButtonVariant
import com.moviecatalog.core.navigator.DestinationRegistry
import com.moviecatalog.core.navigator.HomeDestination
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.navigator.step.Step
import org.koin.compose.koinInject

internal data object MovieCatalogLoginStep : Step() {

    @Composable
    override fun Content() {
        val registry: DestinationRegistry = koinInject()
        val navigator = LocalFlowNavigator.current
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            MovieButton(
                text = "Continue",
                onClick = {
                    navigator.replaceAll(registry.createStep(HomeDestination.Home))
                },
                variant = MovieButtonVariant.Primary,
            )
        }
    }
}
