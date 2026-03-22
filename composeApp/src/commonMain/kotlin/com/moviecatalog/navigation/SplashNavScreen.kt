package com.moviecatalog.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moviecatalog.core.navigator.DestinationRegistry
import com.moviecatalog.core.navigator.RootDestination
import com.moviecatalog.screens.splash.SplashScreen
import org.koin.compose.koinInject

internal object SplashNavScreen : Screen {

    @Composable
    override fun Content() {
        val registry: DestinationRegistry = koinInject()
        val navigator = LocalNavigator.currentOrThrow
        SplashScreen(
            onLoadingComplete = {
                navigator.replaceAll(registry.createScreen(RootDestination.Login))
            },
        )
    }
}
