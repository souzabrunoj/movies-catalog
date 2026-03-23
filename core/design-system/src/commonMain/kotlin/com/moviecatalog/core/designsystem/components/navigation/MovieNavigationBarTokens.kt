package com.moviecatalog.core.designsystem.components.navigation

import androidx.compose.ui.graphics.vector.ImageVector

public enum class MovieNavigationBarBackground {
    Transparent,
    Surface,
    Body,
}

public sealed class MovieNavigationBarLeadingAction {
    public data class Back(public val onClick: () -> Unit) : MovieNavigationBarLeadingAction()
    public data class Close(public val onClick: () -> Unit) : MovieNavigationBarLeadingAction()
}

public data class MovieNavigationBarTrailingAction(
    public val icon: ImageVector,
    public val contentDescription: String?,
    public val onClick: () -> Unit,
)
