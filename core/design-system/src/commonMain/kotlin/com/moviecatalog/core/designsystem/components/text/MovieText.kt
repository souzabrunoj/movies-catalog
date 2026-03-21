package com.moviecatalog.core.designsystem.components.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant

@Composable
fun MovieText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    variant: MovieTextVariant = MovieTextVariant.TextMedium(),
    contentColor: MovieTextColor = MovieTextColor.High,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Clip,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    maxLines: Int = Int.MAX_VALUE,
    softWrap: Boolean = true,
) {
    val style = variant.resolve(MovieTheme.colors, contentColor, textAlign)
    BasicText(
        text = text,
        modifier = modifier,
        style = style,
        onTextLayout = onTextLayout,
        maxLines = maxLines,
        overflow = overflow,
        softWrap = softWrap,
    )
}

@Composable
fun MovieText(
    text: String,
    modifier: Modifier = Modifier,
    variant: MovieTextVariant = MovieTextVariant.TextMedium(),
    contentColor: MovieTextColor = MovieTextColor.High,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Clip,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    maxLines: Int = Int.MAX_VALUE,
    softWrap: Boolean = true,
) {
    MovieText(
        text = AnnotatedString(text),
        modifier = modifier,
        variant = variant,
        contentColor = contentColor,
        textAlign = textAlign,
        overflow = overflow,
        onTextLayout = onTextLayout,
        maxLines = maxLines,
        softWrap = softWrap,
    )
}