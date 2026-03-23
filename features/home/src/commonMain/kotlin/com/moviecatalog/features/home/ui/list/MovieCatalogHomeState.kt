package com.moviecatalog.features.home.ui.list

import com.moviecatalog.core.uimodel.UiModelState

public data class MuseumObjectListItemUiModel(
    val objectId: Int,
    val title: String,
    val cardSubtitle: String,
    val posterImageUrl: String,
)

public data class MovieCatalogHomeState(
    val items: List<MuseumObjectListItemUiModel> = emptyList(),
) : UiModelState
