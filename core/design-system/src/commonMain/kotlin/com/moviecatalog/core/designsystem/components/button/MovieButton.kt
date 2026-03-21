package com.moviecatalog.core.designsystem.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.button.MovieButtonDefaults
import com.moviecatalog.core.designsystem.tokens.button.MovieButtonVariant
import com.moviecatalog.core.designsystem.tokens.size.MovieComponentSize
import com.moviecatalog.core.designsystem.tokens.size.MovieCornerRadius
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieTextStyle


@Composable
fun MovieButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: MovieButtonVariant = MovieButtonVariant.Primary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    loadingText: String? = null,
) {
    val semantic = MovieTheme.colors
    val colors = MovieButtonDefaults.colors(semantic = semantic, variant = variant, enabled = enabled, isLoading = isLoading)
    val label = if (isLoading) loadingText ?: variant.defaultLoadingLabel() else text
    val clickEnabled = enabled && !isLoading
    val interactionSource = remember { MutableInteractionSource() }
    val labelStyle = MovieTheme.textStyle(
        style = MovieTextStyle.TextMedium,
        fontWeight = FontWeight.Bold,
        color = colors.content,
    )

    Row(
        modifier = modifier
            .defaultMinSize(minHeight = MovieComponentSize.MinTouchTarget)
            .clip(RoundedCornerShape(MovieCornerRadius.Small))
            .background(colors.background)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = clickEnabled,
                onClick = onClick,
            )
            .padding(horizontal = MovieSpace.Large, vertical = MovieSpace.Small),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isLoading) {
            MovieButtonSpinner(color = colors.content)
            Spacer(Modifier.width(MovieSpace.XSmall2))
        }
        BasicText(text = AnnotatedString(label), style = labelStyle)
    }
}