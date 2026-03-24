package com.moviecatalog.features.home.list.di

import com.moviecatalog.core.navigator.HomeDestination
import com.moviecatalog.core.navigator.importDestinations
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.features.home.list.data.local.InMemoryMovieStorage
import com.moviecatalog.features.home.list.data.local.MovieStorage
import com.moviecatalog.features.home.list.data.remote.MovieApi
import com.moviecatalog.features.home.list.data.remote.MovieApiImpl
import com.moviecatalog.features.home.list.data.repository.MovieRepositoryImpl
import com.moviecatalog.features.home.list.domain.repository.MovieRepository
import com.moviecatalog.features.home.list.ui.step.MovieCatalogHomeStep
import com.moviecatalog.features.home.list.ui.uiModel.MovieCatalogHomeUiModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val listScreenFactories: Map<HomeDestination, () -> Step> = mapOf(
    HomeDestination.Home to { MovieCatalogHomeStep },
)

internal val listFeatureModule: Module = module {
    viewModelOf(::MovieCatalogHomeUiModel)
    importDestinations(listScreenFactories)

    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<MovieApi> { MovieApiImpl(get()) }
    single<MovieStorage> { InMemoryMovieStorage() }
    single<MovieRepository> {
        MovieRepositoryImpl(get(), get()).apply { initialize() }
    }
}
