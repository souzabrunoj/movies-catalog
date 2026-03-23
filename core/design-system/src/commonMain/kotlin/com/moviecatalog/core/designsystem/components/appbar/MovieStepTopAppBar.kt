package com.moviecatalog.core.designsystem.components.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.moviecatalog.core.designsystem.components.navigation.MovieNavigationBarBackground
import com.moviecatalog.core.designsystem.components.navigation.MovieNavigationBarLeadingAction
import com.moviecatalog.core.designsystem.components.navigation.MovieNavigationBarTrailingAction
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.size.MovieComponentSize
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant

@Composable
public fun MovieStepTopAppBar(
    title: String,
    background: MovieNavigationBarBackground,
    navigationAction: MovieNavigationBarLeadingAction?,
    defaultBackEnabled: Boolean,
    onDefaultBackClick: () -> Unit,
    navigationContentDescription: String?,
    primaryAction: MovieNavigationBarTrailingAction?,
    secondaryAction: MovieNavigationBarTrailingAction?,
    modifier: Modifier = Modifier,
) {
    val colors = MovieTheme.colors
    val barColor = when (background) {
        MovieNavigationBarBackground.Transparent -> colors.transparent
        MovieNavigationBarBackground.Surface -> colors.backgroundSurface
        MovieNavigationBarBackground.Body -> colors.backgroundBody
    }
    Box(
        modifier
            .fillMaxWidth()
            .background(barColor)
            .padding(vertical = MovieSpace.XSmall),
    ) {
        MovieText(
            text = title,
            variant = MovieTextVariant.HeadingSmall(),
            contentColor = MovieTextColor.High,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = MovieComponentSize.MinTouchTarget),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            Modifier
                .align(Alignment.CenterStart)
                .padding(start = MovieSpace.XSmall2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            when (navigationAction) {
                is MovieNavigationBarLeadingAction.Back -> MovieAppBarLeadingIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = navigationContentDescription,
                    onClick = navigationAction.onClick,
                )
                is MovieNavigationBarLeadingAction.Close -> MovieAppBarLeadingIconButton(
                    icon = Icons.Filled.Close,
                    contentDescription = navigationContentDescription,
                    onClick = navigationAction.onClick,
                )
                null -> if (defaultBackEnabled) {
                    MovieAppBarLeadingIconButton(
                        icon = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = navigationContentDescription,
                        onClick = onDefaultBackClick,
                    )
                } else {
                    Box(Modifier.size(MovieComponentSize.IconMedium + MovieSpace.Small * 2))
                }
            }
        }
        Row(
            Modifier
                .align(Alignment.CenterEnd)
                .padding(end = MovieSpace.XSmall2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            secondaryAction?.let { MovieAppBarTrailingIconButton(it) }
            primaryAction?.let { MovieAppBarTrailingIconButton(it) }
        }
    }
}

@Composable
private fun MovieAppBarLeadingIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
) {
    val colors = MovieTheme.colors
    Image(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(MovieSpace.Small)
            .size(MovieComponentSize.IconMedium),
        colorFilter = ColorFilter.tint(colors.contentHigh),
    )
}

@Composable
private fun MovieAppBarTrailingIconButton(action: MovieNavigationBarTrailingAction) {
    val colors = MovieTheme.colors
    Image(
        imageVector = action.icon,
        contentDescription = action.contentDescription,
        modifier = Modifier
            .clickable(onClick = action.onClick)
            .padding(MovieSpace.Small)
            .size(MovieComponentSize.IconMedium),
        colorFilter = ColorFilter.tint(colors.contentHigh),
    )
}
