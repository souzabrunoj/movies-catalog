package com.moviecatalog.features.home.list.data.remote

import com.moviecatalog.features.home.list.data.dto.MoviesResponse

internal interface MovieApi {
     suspend fun getMovies(): MoviesResponse
}
