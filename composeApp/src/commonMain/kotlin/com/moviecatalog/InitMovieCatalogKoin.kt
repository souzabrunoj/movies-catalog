package com.moviecatalog

import com.moviecatalog.core.navigator.navigatorRegistryModule
import com.moviecatalog.features.home.di.homeFeatureModule
import com.moviecatalog.features.login.di.loginFeatureModule
import org.koin.core.context.startKoin
import org.koin.core.error.KoinApplicationAlreadyStartedException

fun initMovieCatalogKoin() {
    try {
        startKoin {
            modules(
                navigatorRegistryModule,
                loginFeatureModule,
                homeFeatureModule,
            )
        }
    } catch (e: KoinApplicationAlreadyStartedException) {
        e.printStackTrace()
    }
}
