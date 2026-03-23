package com.moviecatalog

import androidx.compose.ui.window.ComposeUIViewController
import com.moviecatalog.screens.MovieCatalogApp

fun MainViewController() = ComposeUIViewController {
    KoinIos.ensureKoinStarted()
    MovieCatalogApp()
}
