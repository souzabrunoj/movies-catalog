package com.moviecatalog.features.home.details.domain.repository

import com.moviecatalog.features.home.details.ui.uiModel.state.MovieObjectDetailUiModel
import kotlinx.coroutines.flow.Flow

internal interface MovieDetailsRepository {
    fun getMovieById(movieId: Int): Flow<MovieObjectDetailUiModel>
}
