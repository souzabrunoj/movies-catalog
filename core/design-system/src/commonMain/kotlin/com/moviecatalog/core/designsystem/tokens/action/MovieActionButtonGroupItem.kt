package com.moviecatalog.core.designsystem.tokens.action

import androidx.compose.runtime.Composable

data class MovieActionButtonGroupItem(
    val text: String,
    val leading: (@Composable (selected: Boolean) -> Unit)? = null,
)