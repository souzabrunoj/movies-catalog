package com.moviecatalog.core.designsystem.components.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.snackbar.MovieSnackbarPosition

@Composable
public fun BoxScope.MovieSnackbarHost(
    hostState: MovieSnackbarHostState,
    modifier: Modifier = Modifier,
    position: MovieSnackbarPosition = MovieSnackbarPosition.BottomCenter,
) {
    Box(
        modifier =
            Modifier
                .align(position.toAlignment())
                .fillMaxWidth()
                .then(modifier),
    ) {
        val visual = hostState.current
        if (visual != null) {
            MovieSnackbar(
                message = visual.message,
                variant = visual.variant,
                action = visual.action,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MovieSpace.Medium, vertical = MovieSpace.Small),
            )
        }
    }
}
