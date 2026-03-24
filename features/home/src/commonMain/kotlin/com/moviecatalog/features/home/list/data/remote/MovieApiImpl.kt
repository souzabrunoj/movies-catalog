package com.moviecatalog.features.home.list.data.remote

import com.moviecatalog.features.home.list.data.dto.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.CancellationException

private const val API_URL = "https://souzabrunoj.github.io/movie-catalog-assets/cinegraph/movies/1/movies-list.json"

internal class MovieApiImpl(private val client: HttpClient) : MovieApi {
    override suspend fun getMovies(): MoviesResponse =
        try {
            client.get(API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            MoviesResponse(
                hasMore = false,
                movies = emptyList(),
            )
        }
}
