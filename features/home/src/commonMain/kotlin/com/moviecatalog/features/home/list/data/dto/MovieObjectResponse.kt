package com.moviecatalog.features.home.list.data.dto

import kotlinx.serialization.Serializable

@Serializable
public data class MovieObjectResponse(
    public val objectID: Int,
    public val title: String,
    public val artistDisplayName: String,
    public val medium: String,
    public val dimensions: String,
    public val objectURL: String,
    public val objectDate: String,
    public val primaryImage: String,
    public val primaryImageSmall: String,
    public val repository: String,
    public val department: String,
    public val creditLine: String,
)
