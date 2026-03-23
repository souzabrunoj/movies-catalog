package com.moviecatalog.features.home.ui.list

import com.moviecatalog.core.uimodel.UiMode
import com.moviecatalog.core.uimodel.UiModel
import com.moviecatalog.features.home.data.MuseumRepository
import com.moviecatalog.features.home.ui.toListItemUiModel

internal class MovieCatalogHomeUiModel(private val museumRepository: MuseumRepository) :
    UiModel<MovieCatalogHomeState>(initialData = MovieCatalogHomeState()) {

    public fun getMovies() = setState({
        museumRepository.getObjects().collect { list ->
            updateData {
                copy(items = list.map { it.toListItemUiModel() })
            }
        }
    }, loadingState = { UiMode.Loading("Loading movies...") })
}
