package com.moviecatalog.core.navigator.step

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey

internal class StepBackedScreen(private val step: Step) : Screen {

    override val key: ScreenKey
        get() = step.key

    @Composable
    override fun Content() {
        step.Content()
    }
}
