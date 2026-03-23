package com.moviecatalog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.navigator.AppNavigationHost
import com.moviecatalog.core.navigator.DestinationRegistry
import com.moviecatalog.screens.splash.MovieCatalogSplashStep
import org.koin.compose.koinInject

@Composable
fun MovieCatalogApp() {
    val destinationRegistry: DestinationRegistry = koinInject()
    MovieTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MovieTheme.colors.backgroundBody),
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing),
            ) {
                AppNavigationHost(
                    initialStep = MovieCatalogSplashStep,
                    destinationRegistry = destinationRegistry,
                )
            }
        }
    }
}
