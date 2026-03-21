package com.moviecatalog.core.designsystem.components.action

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as lazyGridItems
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.action.MovieActionButtonGroupItem
import com.moviecatalog.core.designsystem.tokens.action.MovieActionButtonGroupOverflowMode
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieTextStyle

@Composable
fun <T> MovieActionButtonGroup(
    items: List<T>,
    map: (T) -> MovieActionButtonGroupItem,
    selectedItems: Collection<T>,
    onItemSelected: (T) -> Unit,
    overflowMode: MovieActionButtonGroupOverflowMode,
    modifier: Modifier = Modifier,
    itemKey: (T) -> Any = { it.hashCode() },
) {
    when (overflowMode) {
        is MovieActionButtonGroupOverflowMode.Grid ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(overflowMode.columns),
                modifier = modifier,
                contentPadding = overflowMode.contentPadding,
                horizontalArrangement = Arrangement.spacedBy(overflowMode.horizontalSpacing),
                verticalArrangement = Arrangement.spacedBy(overflowMode.verticalSpacing),
            ) {
                lazyGridItems(
                    items = items,
                    key = itemKey,
                ) { item ->
                    val selected = selectedItems.contains(item)
                    MovieActionChipCell(
                        display = map(item),
                        selected = selected,
                        onClick = { onItemSelected(item) },
                    )
                }
            }

        is MovieActionButtonGroupOverflowMode.List ->
            LazyColumn(
                modifier = modifier,
                contentPadding = overflowMode.contentPadding,
                verticalArrangement = Arrangement.spacedBy(overflowMode.verticalSpacing),
            ) {
                items(
                    items = items,
                    key = itemKey,
                ) { item ->
                    val selected = selectedItems.contains(item)
                    MovieActionChipCell(
                        display = map(item),
                        selected = selected,
                        onClick = { onItemSelected(item) },
                    )
                }
            }
    }
}

@Composable
private fun MovieActionChipCell(
    display: MovieActionButtonGroupItem,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val semantic = MovieTheme.colors
    val container = if (selected) semantic.contentPositive else semantic.textFieldFilledBackground
    val label = if (selected) semantic.contentOnSecondaryFill else semantic.contentMedium
    val shape = RoundedCornerShape(percent = 50)
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = MovieSpace.XLarge3)
            .clip(shape)
            .background(container, shape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = MovieSpace.Small, vertical = MovieSpace.XSmall2),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MovieSpace.XSmall2),
    ) {
        display.leading?.invoke(selected)
        BasicText(
            text = AnnotatedString(display.text),
            modifier = Modifier.weight(1f),
            style = MovieTheme.textStyle(MovieTextStyle.TextMedium, FontWeight.Medium, label),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}