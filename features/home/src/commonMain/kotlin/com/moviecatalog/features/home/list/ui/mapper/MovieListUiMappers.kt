package com.moviecatalog.features.home.list.ui.mapper

import com.moviecatalog.features.home.list.ui.MovieObjectListItemUiModel
import com.moviecatalog.features.home.shared.domain.model.MovieObject

internal fun MovieObject.toListItemUiModel(): MovieObjectListItemUiModel =
    MovieObjectListItemUiModel(
        movieId = id,
        title = title,
        cardSubtitle = cardSubtitleLine(),
        posterImageUrl = primaryImageSmall,
    )

private fun MovieObject.cardSubtitleLine(): String {
    val parts = listOf(department, medium).map { it.trim() }.filter { it.isNotEmpty() }
    return if (parts.isNotEmpty()) {
        parts.joinToString(" • ")
    } else {
        artistDisplayName
    }
}
