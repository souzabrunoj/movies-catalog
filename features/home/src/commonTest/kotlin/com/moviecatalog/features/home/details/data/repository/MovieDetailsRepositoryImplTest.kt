package com.moviecatalog.features.home.details.data.repository

import com.moviecatalog.features.home.list.data.local.InMemoryMovieStorage
import com.moviecatalog.features.home.shared.domain.model.Movies
import com.moviecatalog.features.home.test.sampleMovieObject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MovieDetailsRepositoryImplTest {

    @Test
    fun mapsMovieFromStorage() = runTest {
        val movie = sampleMovieObject(id = 5, title = "Detail Title")
        val storage = InMemoryMovieStorage()
        storage.saveMovies(Movies(movies = listOf(movie)))
        val repo = MovieDetailsRepositoryImpl(storage)
        val detail = repo.getMovieById(5).first()
        assertEquals("Detail Title", detail.title)
        assertEquals(movie.artistDisplayName, detail.artistDisplayName)
    }
}
