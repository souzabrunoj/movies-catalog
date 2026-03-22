package com.moviecatalog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.navigator.flow.navigator.FlowNavigator
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.screens.splash.MovieCatalogSplashStep

@Composable
fun MovieCatalogApp() {
    MovieTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MovieTheme.colors.backgroundBody),
        ) {
            Navigator(screen = MovieCatalogSplashStep) { navigator ->
                val flowNavigator = remember(navigator) {
                    FlowNavigator.bind(navigator)
                }
                CompositionLocalProvider(LocalFlowNavigator provides flowNavigator) {
                    CurrentScreen()
                }
            }
        }
    }
}
