package com.moviecatalog.core.designsystem.components.snackbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moviecatalog.core.designsystem.components.icon.MovieIcon
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.icons.MovieIcons
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.size.MovieCornerRadius
import com.moviecatalog.core.designsystem.tokens.size.MovieIconSize
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.snackbar.MovieSnackbarVariant
import com.moviecatalog.core.designsystem.tokens.type.MovieIconColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant

/**
 * Layout aligned with feedback snackbars: rounded card, left accent strip (follows corner clip),
 * circular leading icon, wrapping message, trailing action.
 */
@Composable
public fun MovieSnackbar(
    message: String,
    variant: MovieSnackbarVariant,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    action: MovieSnackbarAction? = null,
) {
    val semantic = MovieTheme.colors
    val accent = variant.accentColor(semantic)
    val container = variant.containerColor(semantic)
    val shape = RoundedCornerShape(MovieCornerRadius.Medium)
    val onSaturated = semantic.contentOnSaturated

    val gap = MovieSpace.Medium
    val padH = MovieSnackbarVariant.ContentHorizontalPadding
    val padV = MovieSnackbarVariant.ContentVerticalPadding

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .clip(shape)
                .background(container),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .heightIn(min = 56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                    Modifier
                        .width(MovieSnackbarVariant.AccentBarWidth)
                        .fillMaxHeight()
                        .background(accent),
            )
            Row(
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(
                            start = padH,
                            top = padV,
                            end = padH,
                            bottom = padV,
                        ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(gap),
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(MovieSnackbarVariant.LeadingIconCircleSize)
                            .clip(CircleShape)
                            .background(accent),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        imageVector = variant.leadingIcon().imageVector,
                        contentDescription = contentDescription,
                        modifier = Modifier.size(MovieSnackbarVariant.LeadingIconGlyphSize),
                        colorFilter = ColorFilter.tint(onSaturated),
                    )
                }
                MovieText(
                    text = message,
                    variant = MovieTextVariant.TextSmall(),
                    contentColor = MovieTextColor.High,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f),
                )
                if (action != null) {
                    when (action) {
                        is MovieSnackbarAction.Text -> {
                            MovieText(
                                text = action.label.uppercase(),
                                variant =
                                    MovieTextVariant.TextSmall(FontWeight.SemiBold).copy(
                                        color = accent,
                                    ),
                                contentColor = MovieTextColor.High,
                                textAlign = TextAlign.Center,
                                modifier =
                                    Modifier.clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = action.onClick,
                                    ).padding(
                                        horizontal = MovieSpace.Small,
                                        vertical = MovieSpace.XSmall,
                                    ),
                            )
                        }

                        is MovieSnackbarAction.DismissIcon -> {
                            Box(
                                modifier =
                                    Modifier
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            onClick = action.onClick,
                                        )
                                        .padding(MovieSpace.Small),
                            ) {
                                MovieIcon(
                                    icon = MovieIcons.Close,
                                    contentDescription = contentDescription,
                                    size = MovieIconSize.Small,
                                    tint = MovieIconColor.Medium,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
