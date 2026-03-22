package com.moviecatalog.features.login.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moviecatalog.core.designsystem.components.button.MovieButton
import com.moviecatalog.core.designsystem.tokens.button.MovieButtonVariant
import com.moviecatalog.core.navigator.DestinationRegistry
import com.moviecatalog.core.navigator.RootDestination
import org.koin.compose.koinInject

/**
 * Login step: user confirms to open the catalog ([RootDestination.Catalog] → list).
 * Does not depend on `:features:home`; only uses [DestinationRegistry] + [RootDestination].
 */
public object LoginNavScreen : Screen {

    @Composable
    override fun Content() {
        val registry: DestinationRegistry = koinInject()
        val navigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            MovieButton(
                text = "Continue",
                onClick = {
                    navigator.replaceAll(registry.createScreen(RootDestination.Catalog))
                },
                variant = MovieButtonVariant.Primary,
            )
        }
    }
}
