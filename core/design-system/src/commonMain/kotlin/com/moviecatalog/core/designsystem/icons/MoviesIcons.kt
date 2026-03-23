package com.moviecatalog.core.designsystem.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.graphics.vector.ImageVector

enum class MovieIcons {
    Email,
    Lock,
    ;

    internal val imageVector: ImageVector
        get() =
            when (this) {
                Email -> Icons.Filled.Email
                Lock -> Icons.Filled.Lock
            }
}