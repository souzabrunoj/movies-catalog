package com.moviecatalog.features.home.list.ui

import com.moviecatalog.core.uimodel.UiModelState

internal data class MovieObjectListItemUiModel(
    val movieId: Int,
    val title: String,
    val cardSubtitle: String,
    val posterImageUrl: String,
)

internal data class MovieCatalogHomeState(
    val items: List<MovieObjectListItemUiModel> = emptyList(),
) : UiModelState
