package com.moviecatalog.features.home.test

import com.moviecatalog.features.home.list.data.dto.MovieObjectResponse
import com.moviecatalog.features.home.list.data.remote.MovieApi

internal class FakeMovieApi(
    private val responses: List<MovieObjectResponse>,
) : MovieApi {
    override suspend fun getMovies(): List<MovieObjectResponse> = responses
}
