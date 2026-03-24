package com.moviecatalog.core.uimodel

public sealed class UiMode {
    public data object Content : UiMode()

    public data class Loading(
        public val message: String? = null,
    ) : UiMode()

    public data class Error(
        public val message: String,
        public val cause: Throwable? = null,
    ) : UiMode()
}
