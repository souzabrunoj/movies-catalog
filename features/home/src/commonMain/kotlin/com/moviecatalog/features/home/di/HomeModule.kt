package com.moviecatalog.features.home.di

import com.moviecatalog.core.navigator.RootDestination
import com.moviecatalog.core.navigator.importDestinations
import com.moviecatalog.features.home.data.InMemoryMuseumStorage
import com.moviecatalog.features.home.data.KtorMuseumApi
import com.moviecatalog.features.home.data.MuseumApi
import com.moviecatalog.features.home.data.MuseumRepository
import com.moviecatalog.features.home.data.MuseumStorage
import com.moviecatalog.features.home.navigation.CatalogListNavScreen
import com.moviecatalog.features.home.ui.detail.DetailViewModel
import com.moviecatalog.features.home.ui.list.ListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

public val homeDataModule: Module = module {
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

public val homeViewModelModule: Module = module {
    factoryOf(::ListViewModel)
    factoryOf(::DetailViewModel)
    importDestinations(RootDestination.Catalog to { CatalogListNavScreen })
}
