package com.moviecatalog.core.designsystem.components.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.moviecatalog.core.designsystem.icons.MovieIcons
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.size.MovieIconSize
import com.moviecatalog.core.designsystem.tokens.type.MovieIconColor

@Composable
fun MovieIcon(
    icon: MovieIcons,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    size: MovieIconSize = MovieIconSize.Medium,
    tint: MovieIconColor = MovieIconColor.Medium,
) {
    val tintColor = tint.resolve(MovieTheme.colors)
    Image(
        imageVector = icon.imageVector,
        contentDescription = contentDescription,
        modifier = modifier.size(size.dp),
        colorFilter = ColorFilter.tint(tintColor),
    )
}