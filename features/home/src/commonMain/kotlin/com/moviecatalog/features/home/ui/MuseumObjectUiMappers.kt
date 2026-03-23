package com.moviecatalog.features.home.ui

import com.moviecatalog.features.home.data.MuseumObject
import com.moviecatalog.features.home.ui.detail.MuseumObjectDetailUiModel
import com.moviecatalog.features.home.ui.list.MuseumObjectListItemUiModel

internal fun MuseumObject.toListItemUiModel(): MuseumObjectListItemUiModel =
    MuseumObjectListItemUiModel(
        objectId = objectID,
        title = title,
        cardSubtitle = cardSubtitleLine(),
        posterImageUrl = primaryImageSmall,
    )

internal fun MuseumObject.toDetailUiModel(): MuseumObjectDetailUiModel =
    MuseumObjectDetailUiModel(
        title = title,
        heroImageUrl = primaryImage.ifBlank { primaryImageSmall },
        artistDisplayName = artistDisplayName,
        objectDate = objectDate,
        dimensions = dimensions,
        medium = medium,
        department = department,
        repository = repository,
        creditLine = creditLine,
    )

private fun MuseumObject.cardSubtitleLine(): String {
    val parts = listOf(department, medium).map { it.trim() }.filter { it.isNotEmpty() }
    return if (parts.isNotEmpty()) {
        parts.joinToString(" • ")
    } else {
        artistDisplayName
    }
}
