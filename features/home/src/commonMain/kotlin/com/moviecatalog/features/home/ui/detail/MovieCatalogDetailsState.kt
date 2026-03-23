package com.moviecatalog.features.home.ui.detail

import com.moviecatalog.core.uimodel.UiModelState

public data class MuseumObjectDetailUiModel(
    val title: String,
    val heroImageUrl: String,
    val artistDisplayName: String,
    val objectDate: String,
    val dimensions: String,
    val medium: String,
    val department: String,
    val repository: String,
    val creditLine: String,
)

public data class MovieCatalogDetailsState(
    val detail: MuseumObjectDetailUiModel? = null,
) : UiModelState
