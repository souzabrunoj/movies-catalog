package com.moviecatalog.core.navigator.step

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moviecatalog.core.designsystem.components.navigation.MovieNavigationBarBackground
import com.moviecatalog.core.designsystem.components.navigation.MovieNavigationBarLeadingAction
import com.moviecatalog.core.designsystem.components.navigation.MovieNavigationBarTrailingAction

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
    val navigationContentDescription: String? = null,
    val background: MovieNavigationBarBackground = MovieNavigationBarBackground.Transparent,
    val navigationAction: MovieNavigationBarLeadingAction? = null,
    val primaryAction: MovieNavigationBarTrailingAction? = null,
    val secondaryAction: MovieNavigationBarTrailingAction? = null,
)
