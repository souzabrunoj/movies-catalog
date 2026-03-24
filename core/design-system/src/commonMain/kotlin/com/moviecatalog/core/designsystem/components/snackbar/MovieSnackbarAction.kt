package com.moviecatalog.core.designsystem.components.snackbar

public sealed class MovieSnackbarAction {
    public data class Text(
        val label: String,
        val onClick: () -> Unit,
    ) : MovieSnackbarAction()

    public data class DismissIcon(
        val onClick: () -> Unit,
    ) : MovieSnackbarAction()
}
