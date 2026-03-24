package com.moviecatalog.features.home.list.domain.repository

import com.moviecatalog.features.home.shared.domain.model.Movies
import kotlinx.coroutines.flow.Flow

internal interface MovieRepository {
    suspend fun refresh()
    fun getMovies(): Flow<Movies>
}
