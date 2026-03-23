package com.moviecatalog.core.designsystem.tokens.snackbar

public enum class MovieSnackbarDuration {
    Short,
    Long,
    ;

    internal val visibleMillis: Long
        get() =
            when (this) {
                Short -> 4_000L
                Long -> 10_000L
            }
}
