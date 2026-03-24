package com.moviecatalog.features.home.list.data.repository

import com.moviecatalog.features.home.list.data.local.MovieStorage
import com.moviecatalog.features.home.list.data.mapper.toDomain
import com.moviecatalog.features.home.list.data.remote.MovieApi
import com.moviecatalog.features.home.list.domain.repository.MovieRepository
import com.moviecatalog.features.home.shared.domain.model.Movies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val movieStorage: MovieStorage,
) : MovieRepository {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    override suspend fun refresh() {
        val responses = movieApi.getMovies()
        movieStorage.saveMovies(
            newMovies = Movies(
                hasMore = responses.hasMore,
                movies = responses.movies.map { it.toDomain() })
        )
    }

    override fun getMovies(): Flow<Movies> = movieStorage.getMovies()
}
