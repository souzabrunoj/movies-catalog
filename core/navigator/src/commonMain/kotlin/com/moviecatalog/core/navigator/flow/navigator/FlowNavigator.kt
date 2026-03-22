package com.moviecatalog.core.navigator.flow.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.moviecatalog.core.navigator.DestinationRegistry
import com.moviecatalog.core.navigator.NavDestination
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.core.navigator.step.StepBackedScreen

public val LocalFlowNavigator: ProvidableCompositionLocal<FlowNavigator> =
    staticCompositionLocalOf { error("FlowNavigator not provided") }

public class FlowNavigator private constructor(
    private val voyagerNavigator: Navigator,
    private val destinationRegistry: DestinationRegistry,
) {

    public fun push(item: Step) {
        voyagerNavigator.push(StepBackedScreen(item))
    }

    public fun push(destination: NavDestination) {
        voyagerNavigator.push(StepBackedScreen(destinationRegistry.createStep(destination)))
    }

    public fun pop(): Boolean =
        voyagerNavigator.pop()

    public fun replaceAll(item: Step) {
        voyagerNavigator.replaceAll(StepBackedScreen(item))
    }

    public fun replaceAll(destination: NavDestination) {
        voyagerNavigator.replaceAll(StepBackedScreen(destinationRegistry.createStep(destination)))
    }

    public fun replace(item: Step) {
        voyagerNavigator.replace(StepBackedScreen(item))
    }

    public fun replace(destination: NavDestination) {
        voyagerNavigator.replace(StepBackedScreen(destinationRegistry.createStep(destination)))
    }

    public val canPop: Boolean
        get() = voyagerNavigator.canPop

    public companion object {

        internal fun bind(navigator: Navigator, destinationRegistry: DestinationRegistry): FlowNavigator =
            FlowNavigator(navigator, destinationRegistry)

        @Composable
        public operator fun invoke(initialStep: Step, destinationRegistry: DestinationRegistry) {
            Navigator(screen = StepBackedScreen(initialStep)) { navigator ->
                val flowNavigator = remember(navigator, destinationRegistry) {
                    FlowNavigator(navigator, destinationRegistry)
                }
                CompositionLocalProvider(LocalFlowNavigator provides flowNavigator) {
                    CurrentScreen()
                }
            }
        }
    }
}
