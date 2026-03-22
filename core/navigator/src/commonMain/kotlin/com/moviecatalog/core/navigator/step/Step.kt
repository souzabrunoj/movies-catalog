package com.moviecatalog.core.navigator.step

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

public typealias StepKey = String

public abstract class Step {

    public open val key: StepKey
        get() = checkNotNull(this::class.simpleName) {
            "Override key for anonymous Step subclasses"
        }

    @Composable
    public abstract fun Content()

    public open val navigationOptions: StepNavigationOptions
        @Composable
        get() = remember { StepNavigationOptions() }
}

public data class StepNavigationOptions(
    val title: String = "",
    val isVisible: Boolean = true,
    val showNavigationAction: Boolean = true,
)
