package com.moviecatalog.features.home.list.ui.uiModel

import com.moviecatalog.core.uimodel.UiMode
import com.moviecatalog.core.uimodel.UiModel
import com.moviecatalog.features.home.list.domain.mapper.toListItemUiModel
import com.moviecatalog.features.home.list.domain.repository.MovieRepository
import com.moviecatalog.features.home.list.ui.uiModel.state.MovieCatalogHomeState
import kotlinx.coroutines.delay

internal class MovieCatalogHomeUiModel(private val movieRepository: MovieRepository) :
    UiModel<MovieCatalogHomeState>(
        initialData = MovieCatalogHomeState(),
        initialMode = UiMode.Loading(LOADING_MOVIES_MESSAGE),
    ) {

    internal fun getMovies() = setState({
        delay(1000)
        movieRepository.getMovies().collect { list ->
            updateData {
                copy(items = list.map { it.toListItemUiModel() })
            }
        }
    }, loadingState = { UiMode.Loading(LOADING_MOVIES_MESSAGE) })

    private companion object {
        private const val LOADING_MOVIES_MESSAGE: String = "Loading movies..."
    }
}
