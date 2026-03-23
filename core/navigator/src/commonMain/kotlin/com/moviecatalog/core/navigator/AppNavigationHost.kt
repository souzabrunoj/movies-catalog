package com.moviecatalog.core.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import com.moviecatalog.core.navigator.flow.navigator.FlowNavigator
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.navigator.step.MovieAppStepHost
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.core.navigator.step.StepBackedScreen

@Composable
public fun AppNavigationHost(initialStep: Step, destinationRegistry: DestinationRegistry) {
    Navigator(screen = StepBackedScreen(initialStep)) { navigator ->
        val flowNavigator = remember(navigator, destinationRegistry) {
            FlowNavigator.bind(navigator, destinationRegistry)
        }
        CompositionLocalProvider(LocalFlowNavigator provides flowNavigator) {
            MovieAppStepHost()
        }
    }
}
