package com.moviecatalog.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import com.moviecatalog.core.designsystem.theme.MovieCatalogTheme
import com.moviecatalog.core.designsystem.tokens.type.MovieCatalogTextStyle
import com.moviecatalog.generated.resources.Res
import com.moviecatalog.generated.resources.no_data_available
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmptyScreenContent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        BasicText(
            text = AnnotatedString(stringResource(Res.string.no_data_available)),
            style = MovieCatalogTheme.textStyle(MovieCatalogTextStyle.TextMedium),
        )
    }
}
