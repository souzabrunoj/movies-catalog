package com.moviecatalog.core.designsystem.components.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.moviecatalog.core.designsystem.tokens.snackbar.MovieSnackbarDuration
import com.moviecatalog.core.designsystem.tokens.snackbar.MovieSnackbarVariant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Stable
public class MovieSnackbarHostState(
    private val scope: CoroutineScope,
) {
    internal var current by mutableStateOf<MovieSnackbarVisual?>(null)
        private set

    private var hideJob: Job? = null

    public fun show(
        message: String,
        duration: MovieSnackbarDuration = MovieSnackbarDuration.Short,
        variant: MovieSnackbarVariant = MovieSnackbarVariant.Info,
        action: MovieSnackbarAction? = null,
    ) {
        hideJob?.cancel()
        val visual = MovieSnackbarVisual(message = message, variant = variant, action = action)
        current = visual
        hideJob =
            scope.launch {
                delay(duration.visibleMillis)
                if (current === visual) {
                    current = null
                }
            }
    }

    public fun dismiss() {
        hideJob?.cancel()
        hideJob = null
        current = null
    }
}

internal data class MovieSnackbarVisual(
    val message: String,
    val variant: MovieSnackbarVariant,
    val action: MovieSnackbarAction?,
)

@Composable
public fun rememberMovieSnackbarHostState(): MovieSnackbarHostState {
    val scope = rememberCoroutineScope()
    return remember { MovieSnackbarHostState(scope) }
}
