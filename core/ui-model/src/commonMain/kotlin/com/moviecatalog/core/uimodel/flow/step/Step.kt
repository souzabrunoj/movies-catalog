package com.moviecatalog.core.uimodel.flow.step

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.screenModule
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey

public typealias StepRegistry = ScreenRegistry

public typealias StepModule = ScreenRegistry.() -> Unit

public typealias StepKey = ScreenKey

public abstract class Step : Screen {

    override val key: StepKey = uniqueScreenKey

    public open val navigationOptions: StepNavigationOptions
        @Composable
        get() = remember { StepNavigationOptions() }
}

public data class StepNavigationOptions(
    val title: String = "",
    val isVisible: Boolean = true,
    val showNavigationAction: Boolean = true,
)

@Composable
public inline fun <reified T : ScreenProvider> rememberStep(
    provider: T,
    key: Any? = null,
): Step =
    remember(provider, key) {
        ScreenRegistry.get(provider) as Step
    }

public fun stepModule(block: StepModule): StepModule = screenModule(block)
