package com.moviecatalog.core.designsystem.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.moviecatalog.core.designsystem.components.button.MovieButtonSpinner
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.color.MovieSemanticColors
import com.moviecatalog.core.designsystem.tokens.input.MovieTextInputSize
import com.moviecatalog.core.designsystem.tokens.input.MovieTextInputTokens
import com.moviecatalog.core.designsystem.tokens.size.MovieCornerRadius
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.size.MovieStrokeWidth
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextStyle
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant

private enum class MovieTextInputUiState {
    Default,
    Disabled,
    Error,
    Loading,
}

@Composable
fun MovieTextInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    size: MovieTextInputSize = MovieTextInputSize.Medium,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    label: String? = null,
    supportText: String? = null,
    errorText: String? = null,
    placeholder: String? = null,
    optional: Boolean = false,
    readOnly: Boolean = false,
    disabled: Boolean = false,
    isLoading: Boolean = false,
    autoFocus: Boolean = false,
    hideCounter: Boolean = false,
    maxLength: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val semantic = MovieTheme.colors
    val state = remember(disabled, errorText, isLoading) {
        when {
            disabled -> MovieTextInputUiState.Disabled
            errorText.isNullOrBlank().not() -> MovieTextInputUiState.Error
            isLoading -> MovieTextInputUiState.Loading
            else -> MovieTextInputUiState.Default
        }
    }
    val cropped = remember(value, maxLength) {
        value.copy(text = value.text.take(maxLength))
    }
    val showCounter = remember(hideCounter, maxLength) {
        !hideCounter && maxLength != Int.MAX_VALUE
    }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(autoFocus, isLoading, disabled) {
        if (autoFocus && !isLoading && !disabled) focusRequester.requestFocus()
    }
    val interactionSource = remember { MutableInteractionSource() }
    val visualTransformationResolved =
        remember(visualTransformation, cropped.text, placeholder) {
            if (cropped.text.isEmpty() && placeholder != null) {
                VisualTransformation.None
            } else {
                visualTransformation
            }
        }
    val minH = MovieTextInputTokens.minFieldHeight(size)
    val fieldShape = RoundedCornerShape(MovieCornerRadius.Small)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(MovieTextInputTokens.wrapperVerticalSpacing),
    ) {
        MovieTextInputHeader(
            label = label,
            optional = optional,
            disabled = disabled,
        )
        BasicTextField(
            value = cropped,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            enabled = !disabled && !isLoading,
            readOnly = readOnly,
            textStyle = inputTextStyle(semantic, state),
            visualTransformation = visualTransformationResolved,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            onTextLayout = onTextLayout,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(
                when (state) {
                    MovieTextInputUiState.Disabled -> semantic.contentDisabled
                    MovieTextInputUiState.Loading -> Color.Transparent
                    else -> semantic.contentHigh
                },
            ),
            singleLine = true,
            maxLines = 1,
            decorationBox = { innerTextField ->
                MovieTextInputDecoration(
                    innerTextField = innerTextField,
                    text = cropped.text,
                    placeholder = placeholder,
                    semantic = semantic,
                    state = state,
                    minHeight = minH,
                    fieldShape = fieldShape,
                    leading = leading,
                    trailing = trailing,
                    readOnly = readOnly,
                    disabled = disabled,
                    onClick = onClick,
                    interactionSource = interactionSource,
                )
            },
        )
        MovieTextInputFooter(
            errorText = errorText,
            supportText = supportText,
            showCounter = showCounter,
            length = cropped.text.length,
            maxLength = maxLength,
        )
    }
}

@Composable
private fun inputTextStyle(
    semantic: MovieSemanticColors,
    state: MovieTextInputUiState,
) = MovieTheme.textStyle(
    style = MovieTextStyle.TextMedium,
    color =
        when (state) {
            MovieTextInputUiState.Disabled -> semantic.contentDisabled
            else -> semantic.contentHigh
        },
)

@Composable
private fun MovieTextInputHeader(
    label: String?,
    optional: Boolean,
    disabled: Boolean,
) {
    val show = !label.isNullOrBlank() || optional
    if (!show) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (label != null) {
            MovieText(
                text = label,
                modifier = Modifier.weight(1f),
                variant = MovieTextVariant.TextMedium(FontWeight.Medium),
                contentColor =
                    if (disabled) {
                        MovieTextColor.Disabled
                    } else {
                        MovieTextColor.Medium
                    },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (optional) {
            if (label != null) {
                Spacer(Modifier.width(MovieTextInputTokens.labelTrailingGap))
            }
            MovieText(
                text = MovieTextInputTokens.OPTIONAL_INDICATOR,
                variant = MovieTextVariant.TextSmall(),
                contentColor = MovieTextColor.Low,
            )
        }
    }
}

@Composable
private fun MovieTextInputDecoration(
    innerTextField: @Composable () -> Unit,
    text: String,
    placeholder: String?,
    semantic: MovieSemanticColors,
    state: MovieTextInputUiState,
    minHeight: Dp,
    fieldShape: RoundedCornerShape,
    leading: (@Composable () -> Unit)?,
    trailing: (@Composable () -> Unit)?,
    readOnly: Boolean,
    disabled: Boolean,
    onClick: (() -> Unit)?,
    interactionSource: MutableInteractionSource,
) {
    val bg =
        when (state) {
            MovieTextInputUiState.Disabled -> semantic.fillNeutralDisabled
            else -> semantic.fillNeutral
        }
    val hasError = state == MovieTextInputUiState.Error
    val rowModifier =
        Modifier
            .fillMaxWidth()
            .clip(fieldShape)
            .background(color = bg, shape = fieldShape)
            .then(
                if (hasError) {
                    Modifier.border(
                        width = MovieStrokeWidth.Hairline,
                        color = semantic.fillDestructive,
                        shape = fieldShape,
                    )
                } else {
                    Modifier
                },
            )
            .padding(
                horizontal = MovieTextInputTokens.fieldHorizontalPadding,
                vertical = MovieTextInputTokens.fieldVerticalPadding,
            )
            .defaultMinSize(minHeight = minHeight)
            .then(
                if (readOnly && onClick != null && !disabled &&
                    state != MovieTextInputUiState.Loading &&
                    state != MovieTextInputUiState.Disabled
                ) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick,
                    )
                } else {
                    Modifier
                },
            )
    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leading != null) {
            leading()
            Spacer(Modifier.width(MovieTextInputTokens.leadingGap))
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart,
        ) {
            if (text.isEmpty() && placeholder != null) {
                val phColor =
                    when (state) {
                        MovieTextInputUiState.Disabled -> semantic.contentDisabled
                        else -> semantic.contentMedium
                    }
                BasicText(
                    text = AnnotatedString(placeholder),
                    style = MovieTheme.textStyle(MovieTextStyle.TextMedium, color = phColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            innerTextField()
        }
        when {
            hasError -> {
                Spacer(Modifier.width(MovieTextInputTokens.trailingGap))
                MovieTextInputErrorBadge()
            }
            state == MovieTextInputUiState.Loading -> {
                Spacer(Modifier.width(MovieTextInputTokens.trailingGap))
                MovieButtonSpinner(color = semantic.contentHigh)
            }
            trailing != null -> {
                Spacer(Modifier.width(MovieTextInputTokens.trailingGap))
                trailing()
            }
        }
    }
}

@Composable
private fun MovieTextInputErrorBadge() {
    val c = MovieTheme.colors
    Box(
        modifier =
            Modifier
                .size(MovieTextInputTokens.errorIndicatorDiameter)
                .clip(CircleShape)
                .background(c.fillDestructive),
        contentAlignment = Alignment.Center,
    ) {
        BasicText(
            text = AnnotatedString("!"),
            style =
                MovieTheme.textStyle(
                    style = MovieTextStyle.TextSmall,
                    fontWeight = FontWeight.Bold,
                    color = c.contentOnBrandHigh,
                ).copy(textAlign = TextAlign.Center),
        )
    }
}

@Composable
private fun MovieTextInputFooter(
    errorText: String?,
    supportText: String?,
    showCounter: Boolean,
    length: Int,
    maxLength: Int,
) {
    val hasError = !errorText.isNullOrBlank()
    val message =
        when {
            hasError -> errorText!!
            else -> supportText
        }
    if (message == null && !showCounter) return

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        if (message != null) {
            val variant =
                if (hasError) {
                    MovieTextVariant(
                        textStyle = MovieTextStyle.TextSmall,
                        color = MovieTheme.colors.fillDestructive,
                    )
                } else {
                    MovieTextVariant(
                        textStyle = MovieTextStyle.TextSmall,
                        color = MovieTheme.colors.contentLow,
                    )
                }
            MovieText(
                text = message,
                modifier = Modifier.weight(1f),
                variant = variant,
                contentColor = MovieTextColor.Medium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        } else {
            Spacer(Modifier.weight(1f))
        }
        if (showCounter) {
            if (message != null) {
                Spacer(Modifier.width(MovieSpace.Small))
            }
            MovieText(
                text = "$length / $maxLength",
                variant =
                    MovieTextVariant(
                        textStyle = MovieTextStyle.TextSmall,
                        color = MovieTheme.colors.contentLow,
                    ),
                contentColor = MovieTextColor.Medium,
            )
        }
    }
}
