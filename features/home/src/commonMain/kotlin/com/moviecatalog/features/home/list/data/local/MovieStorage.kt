package com.moviecatalog.features.home.list.data.local

import com.moviecatalog.features.home.shared.domain.model.MovieObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

internal interface MovieStorage {
    suspend fun saveMovies(newMovies: List<MovieObject>)

    fun getMovieById(movieId: Int): Flow<MovieObject?>

    fun getMovies(): Flow<List<MovieObject>>
}

internal class InMemoryMovieStorage : MovieStorage {
    private val storedMovies = MutableStateFlow(emptyList<MovieObject>())

    override suspend fun saveMovies(newMovies: List<MovieObject>) {
        storedMovies.value = newMovies
    }

    override fun getMovieById(movieId: Int): Flow<MovieObject?> =
        storedMovies.map { movies ->
            movies.find { it.id == movieId }
        }

    override fun getMovies(): Flow<List<MovieObject>> = storedMovies
}
