package com.moviecatalog.features.home.details.di

import com.moviecatalog.features.home.details.data.repository.MovieDetailsRepositoryImpl
import com.moviecatalog.features.home.details.domain.repository.MovieDetailsRepository
import com.moviecatalog.features.home.details.ui.uiModel.MovieCatalogDetailsUiModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val detailsFeatureModule: Module = module {
    viewModel { params ->
        MovieCatalogDetailsUiModel(
            movieDetailsRepository = get(),
            movieId = params.get<Int>(),
        )
    }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(get()) }
}
