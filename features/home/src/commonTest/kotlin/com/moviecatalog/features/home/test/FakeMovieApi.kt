package com.moviecatalog.features.home.test

import com.moviecatalog.features.home.list.data.dto.MoviesResponse
import com.moviecatalog.features.home.list.data.remote.MovieApi

internal class FakeMovieApi(
    private val responses: MoviesResponse,
) : MovieApi {
    override suspend fun getMovies(): MoviesResponse = responses
}
