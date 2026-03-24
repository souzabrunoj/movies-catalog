package com.moviecatalog.features.home.details.data.repository

import com.moviecatalog.features.home.details.domain.mappers.toDetailUiModel
import com.moviecatalog.features.home.details.domain.repository.MovieDetailsRepository
import com.moviecatalog.features.home.details.ui.uiModel.state.MovieObjectDetailUiModel
import com.moviecatalog.features.home.list.data.local.MovieStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class MovieDetailsRepositoryImpl(private val movieStorage: MovieStorage) : MovieDetailsRepository {

    override fun getMovieById(movieId: Int): Flow<MovieObjectDetailUiModel> =
        movieStorage.getMovieById(movieId).map { it.toDetailUiModel() }
}
