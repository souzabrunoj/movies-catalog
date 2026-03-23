package com.moviecatalog.features.home.details.domain.mappers

import com.moviecatalog.features.home.details.ui.uiModel.state.MovieObjectDetailUiModel
import com.moviecatalog.features.home.shared.domain.model.MovieObject

internal fun MovieObject?.toDetailUiModel(): MovieObjectDetailUiModel {
    val image = this?.primaryImage
        .takeUnless { it.isNullOrBlank() }
        ?: this?.primaryImageSmall.orEmpty()

    return MovieObjectDetailUiModel(
        title = this?.title.orEmpty(),
        heroImageUrl = image,
        artistDisplayName = this?.artistDisplayName.orEmpty(),
        objectDate = this?.objectDate.orEmpty(),
        dimensions = this?.dimensions.orEmpty(),
        medium = this?.medium.orEmpty(),
        department = this?.department.orEmpty(),
        repository = this?.repository.orEmpty(),
        creditLine = this?.creditLine.orEmpty(),
    )
}
