package com.moviecatalog.features.home.list.data.local

import com.moviecatalog.features.home.shared.domain.model.MovieObject
import com.moviecatalog.features.home.shared.domain.model.Movies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal interface MovieStorage {
    suspend fun saveMovies(newMovies: Movies)

    fun getMovieById(movieId: Int): Flow<MovieObject?>

    fun getMovies(): Flow<Movies>
}

internal class InMemoryMovieStorage : MovieStorage {
    private val storedMovies = MutableStateFlow(Movies())

    override suspend fun saveMovies(newMovies: Movies) {
        storedMovies.value = newMovies
    }

    override fun getMovieById(movieId: Int): Flow<MovieObject?> =
     storedMovies.map { it.movies.find { item -> item.id == movieId } }

    override fun getMovies(): Flow<Movies> = storedMovies
}
