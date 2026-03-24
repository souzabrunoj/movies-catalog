package com.moviecatalog.core.designsystem.tokens.snackbar

import androidx.compose.ui.Alignment

public enum class MovieSnackbarPosition {
    TopCenter,
    BottomCenter,
    ;

    internal fun toAlignment(): Alignment =
        when (this) {
            TopCenter -> Alignment.TopCenter
            BottomCenter -> Alignment.BottomCenter
        }
}
