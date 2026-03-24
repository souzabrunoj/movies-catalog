package com.moviecatalog.features.home.list.data.mapper

import com.moviecatalog.features.home.list.data.dto.MovieObjectResponse
import com.moviecatalog.features.home.shared.domain.model.MovieObject

internal fun MovieObjectResponse.toDomain(): MovieObject =
    MovieObject(
        id = objectID,
        title = title,
        artistDisplayName = artistDisplayName,
        medium = medium,
        dimensions = dimensions,
        objectURL = objectURL,
        objectDate = objectDate,
        primaryImage = primaryImage,
        primaryImageSmall = primaryImageSmall,
        repository = repository,
        department = department,
        creditLine = creditLine,
    )
