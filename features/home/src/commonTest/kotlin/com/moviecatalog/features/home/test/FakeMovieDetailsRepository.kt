package com.moviecatalog.features.home.test

import com.moviecatalog.features.home.details.domain.repository.MovieDetailsRepository
import com.moviecatalog.features.home.details.ui.uiModel.state.MovieObjectDetailUiModel
import kotlinx.coroutines.flow.Flow

internal class FakeMovieDetailsRepository(
    private val flowFactory: (Int) -> Flow<MovieObjectDetailUiModel>,
) : MovieDetailsRepository {
    override fun getMovieById(movieId: Int): Flow<MovieObjectDetailUiModel> = flowFactory(movieId)
}
