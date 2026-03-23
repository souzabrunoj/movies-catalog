package com.moviecatalog.features.home.ui.detail

import com.moviecatalog.core.uimodel.UiMode
import com.moviecatalog.core.uimodel.UiModel
import com.moviecatalog.features.home.data.MuseumRepository
import com.moviecatalog.features.home.ui.toDetailUiModel

internal class MovieCatalogDetailsUiModel(
    private val museumRepository: MuseumRepository,
    private val movieId: Int,
) : UiModel<MovieCatalogDetailsState>(
    initialData = MovieCatalogDetailsState(),
    initialMode = UiMode.Loading(),
) {

    public fun getMovieDetails(): Unit = setState({
        var firstEmission = true
        museumRepository.getObjectById(movieId).collect { obj ->
            if (firstEmission) {
                firstEmission = false
                setStateAndAwait(loadingState = { null }) {
                    currentData.copy(detail = obj?.toDetailUiModel())
                }
            } else {
                updateData {
                    copy(detail = obj?.toDetailUiModel())
                }
            }
        }
    })
}
