package com.moviecatalog.core.designsystem.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

enum class MovieIcons {
    Email,
    Lock,
    Person,
    Visibility,
    VisibilityOff,
    CheckCircle,
    Close,
    Error,
    Info,
    Warning,
    ;

    internal val imageVector: ImageVector
        get() =
            when (this) {
                Email -> Icons.Filled.Email
                Lock -> Icons.Filled.Lock
                Person -> Icons.Filled.Person
                Visibility -> Icons.Filled.Visibility
                VisibilityOff -> Icons.Filled.VisibilityOff
                CheckCircle -> Icons.Filled.CheckCircle
                Close -> Icons.Filled.Close
                Error -> Icons.Filled.Error
                Info -> Icons.Filled.Info
                Warning -> Icons.Filled.Warning
            }
}
