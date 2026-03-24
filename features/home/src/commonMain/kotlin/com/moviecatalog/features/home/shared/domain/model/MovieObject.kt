package com.moviecatalog.features.home.shared.domain.model

internal data class Movies(
    val hasMore: Boolean = false,
    val movies: List<MovieObject> = emptyList(),
)

internal data class MovieObject(
    val id: Int,
    val title: String,
    val artistDisplayName: String,
    val medium: String,
    val dimensions: String,
    val objectURL: String,
    val objectDate: String,
    val primaryImage: String,
    val primaryImageSmall: String,
    val repository: String,
    val department: String,
    val creditLine: String,
)
