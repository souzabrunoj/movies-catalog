package com.moviecatalog.features.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.features.home.generated.resources.Res
import com.moviecatalog.features.home.generated.resources.no_data_available
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun EmptyScreenContent(modifier: Modifier = Modifier, ) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        MovieText(
            text = stringResource(Res.string.no_data_available),
            variant = MovieTextVariant.TextMedium(),
            contentColor = MovieTextColor.Medium,
        )
    }
}
