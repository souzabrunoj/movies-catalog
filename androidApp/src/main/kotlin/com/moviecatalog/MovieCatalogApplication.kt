package com.moviecatalog

import android.app.Application
import com.moviecatalog.core.navigator.navigatorRegistryModule
import com.moviecatalog.features.home.di.homeFeatureModule
import com.moviecatalog.features.login.di.loginFeatureModule
import org.koin.core.context.startKoin

class MovieCatalogApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                navigatorRegistryModule,
                loginFeatureModule,
                homeFeatureModule,
            )
        }
    }
}
