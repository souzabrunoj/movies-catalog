package com.moviecatalog.features.home.test

import com.moviecatalog.features.home.list.domain.repository.MovieRepository
import com.moviecatalog.features.home.shared.domain.model.Movies
import kotlinx.coroutines.flow.Flow

internal class FakeMovieRepository(
    private val moviesFlow: Flow<Movies>,
) : MovieRepository {
    override suspend fun refresh() = Unit

    override fun getMovies(): Flow<Movies> = moviesFlow
}
