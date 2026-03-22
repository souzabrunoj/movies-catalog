package com.moviecatalog

import androidx.compose.ui.window.ComposeUIViewController
import com.moviecatalog.screens.splash.MovieCatalogApp

fun MainViewController() = ComposeUIViewController {
    ensureKoinStarted()
    MovieCatalogApp()
}
