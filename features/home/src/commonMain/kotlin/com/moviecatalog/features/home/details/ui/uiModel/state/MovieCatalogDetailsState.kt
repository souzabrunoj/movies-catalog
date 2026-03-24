package com.moviecatalog.features.home.details.ui.uiModel.state

import com.moviecatalog.core.uimodel.UiModelState

internal data class MovieObjectDetailUiModel(
    val title: String = "",
    val heroImageUrl: String = "",
    val artistDisplayName: String = "",
    val objectDate: String = "",
    val dimensions: String = "",
    val medium: String = "",
    val department: String = "",
    val repository: String = "",
    val creditLine: String = "",
)

internal data class MovieCatalogDetailsState(
    val detail: MovieObjectDetailUiModel = MovieObjectDetailUiModel(),
) : UiModelState
