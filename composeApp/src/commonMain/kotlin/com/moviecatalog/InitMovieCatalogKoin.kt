package com.moviecatalog

import com.moviecatalog.core.database.di.coreDatabaseKoinModule
import com.moviecatalog.core.navigator.navigatorRegistryModule
import com.moviecatalog.features.home.di.homeFeatureModule
import com.moviecatalog.features.login.di.loginFeatureModule
import org.koin.core.context.startKoin
import org.koin.core.error.KoinApplicationAlreadyStartedException
import org.koin.core.module.Module

fun movieCatalogKoinModules(): List<Module> =
    listOf(
        navigatorRegistryModule,
        coreDatabaseKoinModule,
        loginFeatureModule,
        homeFeatureModule,
    )

fun initMovieCatalogKoin() {
    try {
        startKoin {
            modules(movieCatalogKoinModules())
        }
    } catch (e: KoinApplicationAlreadyStartedException) {
        e.printStackTrace()
    }
}
