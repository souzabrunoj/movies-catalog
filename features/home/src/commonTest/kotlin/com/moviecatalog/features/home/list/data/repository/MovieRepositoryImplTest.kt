package com.moviecatalog.features.home.list.data.repository

import com.moviecatalog.features.home.list.data.dto.MoviesResponse
import com.moviecatalog.features.home.list.data.local.InMemoryMovieStorage
import com.moviecatalog.features.home.test.FakeMovieApi
import com.moviecatalog.features.home.test.sampleMovieObjectResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MovieRepositoryImplTest {

    @Test
    fun refresh_persistsMappedMovies() = runTest {
        val dto = sampleMovieObjectResponse(id = 1, title = "A")
        val api = FakeMovieApi(MoviesResponse(hasMore = false, movies = listOf(dto)))
        val storage = InMemoryMovieStorage()
        val repo = MovieRepositoryImpl(api, storage)
        repo.refresh()
        val stored = storage.getMovies().first()
        assertEquals(1, stored.movies.size)
        assertEquals(1, stored.movies.first().id)
        assertEquals("A", stored.movies.first().title)
    }
}
