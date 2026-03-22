package com.moviecatalog

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    ensureKoinStarted()
    App()
}
