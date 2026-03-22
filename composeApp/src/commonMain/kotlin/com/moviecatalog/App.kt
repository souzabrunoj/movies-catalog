package com.moviecatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.navigation.SplashNavScreen

@Composable
fun App() {
    MovieTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(MovieTheme.colors.backgroundBody),
        ) {
            Navigator(screen = SplashNavScreen)
        }
    }
}
