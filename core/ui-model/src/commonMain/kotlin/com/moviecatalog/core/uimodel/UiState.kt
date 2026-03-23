package com.moviecatalog.core.uimodel

public data class UiState<T : UiModelState>(
    public val data: T,
    public val mode: UiMode = UiMode.Content,
)
