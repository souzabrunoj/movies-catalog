package com.moviecatalog.core.navigator.flow.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.moviecatalog.core.uimodel.flow.step.Step

public val LocalFlowNavigator: ProvidableCompositionLocal<FlowNavigator> =
    staticCompositionLocalOf { error("FlowNavigator not provided") }

public class FlowNavigator private constructor(
    private val voyagerNavigator: Navigator,
) {

    public fun push(item: Screen) {
        voyagerNavigator.push(item)
    }

    public fun pop(): Boolean =
        voyagerNavigator.pop()

    public fun replaceAll(item: Screen) {
        voyagerNavigator.replaceAll(item)
    }

    public val canPop: Boolean
        get() = voyagerNavigator.canPop

    public companion object {

        public fun bind(navigator: Navigator): FlowNavigator =
            FlowNavigator(navigator)

        @Composable
        public operator fun invoke(initialStep: Step) {
            Navigator(screen = initialStep) { navigator ->
                val flowNavigator = remember(navigator) {
                    FlowNavigator(navigator)
                }
                CompositionLocalProvider(LocalFlowNavigator provides flowNavigator) {
                    CurrentScreen()
                }
            }
        }
    }
}
