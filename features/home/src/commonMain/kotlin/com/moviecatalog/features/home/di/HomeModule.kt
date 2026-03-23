package com.moviecatalog.features.home.di

import com.moviecatalog.core.navigator.HomeDestination
import com.moviecatalog.core.navigator.importDestinations
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.features.home.data.InMemoryMuseumStorage
import com.moviecatalog.features.home.data.KtorMuseumApi
import com.moviecatalog.features.home.data.MuseumApi
import com.moviecatalog.features.home.data.MuseumRepository
import com.moviecatalog.features.home.data.MuseumStorage
import com.moviecatalog.features.home.ui.detail.MovieCatalogDetailsUiModel
import com.moviecatalog.features.home.ui.list.MovieCatalogHomeStep
import com.moviecatalog.features.home.ui.list.MovieCatalogHomeUiModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val homeScreenFactories: Map<HomeDestination, () -> Step> = mapOf(
    HomeDestination.Home to { MovieCatalogHomeStep },
)

public val homeFeatureModule: Module = module {
    viewModelOf(::MovieCatalogHomeUiModel)
    viewModel { params ->
        MovieCatalogDetailsUiModel(
            museumRepository = get(),
            movieId = params.get<Int>(),
        )
    }
    importDestinations(homeScreenFactories)

    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<MuseumApi> { KtorMuseumApi(get()) }
    single<MuseumStorage> { InMemoryMuseumStorage() }
    single {
        MuseumRepository(get(), get()).apply {
            initialize()
        }
    }
}
