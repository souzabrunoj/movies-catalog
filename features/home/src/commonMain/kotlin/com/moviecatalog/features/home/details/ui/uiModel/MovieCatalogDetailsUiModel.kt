package com.moviecatalog.features.home.details.ui.uiModel

import com.moviecatalog.core.uimodel.UiMode
import com.moviecatalog.core.uimodel.UiModel
import com.moviecatalog.features.home.details.domain.repository.MovieDetailsRepository
import com.moviecatalog.features.home.details.ui.uiModel.state.MovieCatalogDetailsState

internal class MovieCatalogDetailsUiModel(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val movieId: Int,
) : UiModel<MovieCatalogDetailsState>(
    initialData = MovieCatalogDetailsState(),
    initialMode = UiMode.Loading(),
) {

    internal fun getMovieDetails(): Unit = setState({
        var firstEmission = true
        movieDetailsRepository.getMovieById(movieId).collect { obj ->
            if (firstEmission) {
                firstEmission = false
                setStateAndAwait(loadingState = { null }) {
                    currentData.copy(detail = obj)
                }
            } else {
                updateData {
                    copy(detail = obj)
                }
            }
        }
    })
}