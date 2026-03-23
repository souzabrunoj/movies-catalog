package com.moviecatalog.features.home.list.data.remote

import com.moviecatalog.features.home.list.data.dto.MovieObjectResponse

internal interface MovieApi {
     suspend fun getMovies(): List<MovieObjectResponse>
}
