package com.moviecatalog.features.home.test

import com.moviecatalog.features.home.list.data.dto.MovieObjectResponse
import com.moviecatalog.features.home.shared.domain.model.MovieObject

internal fun sampleMovieObjectResponse(
    id: Int = 42,
    title: String = "The Title",
) = MovieObjectResponse(
    objectID = id,
    title = title,
    artistDisplayName = "Artist",
    medium = "Oil",
    dimensions = "10x10",
    objectURL = "https://example.com",
    objectDate = "1900",
    primaryImage = "https://img/large.jpg",
    primaryImageSmall = "https://img/small.jpg",
    repository = "Met",
    department = "Paintings",
    creditLine = "Gift",
)

internal fun sampleMovieObject(
    id: Int = 42,
    title: String = "The Title",
    department: String = "Paintings",
    medium: String = "Oil",
    artistDisplayName: String = "Artist",
) = MovieObject(
    id = id,
    title = title,
    artistDisplayName = artistDisplayName,
    medium = medium,
    dimensions = "10x10",
    objectURL = "https://example.com",
    objectDate = "1900",
    primaryImage = "https://img/large.jpg",
    primaryImageSmall = "https://img/small.jpg",
    repository = "Met",
    department = department,
    creditLine = "Gift",
)
