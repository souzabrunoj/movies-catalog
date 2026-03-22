package com.moviecatalog

import android.app.Application
import com.moviecatalog.core.navigator.navigatorRegistryModule
import com.moviecatalog.features.home.di.homeDataModule
import com.moviecatalog.features.home.di.homeViewModelModule
import com.moviecatalog.features.login.di.loginFeatureModule
import org.koin.core.context.startKoin

class MovieCatalogApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                navigatorRegistryModule,
                loginFeatureModule,
                homeDataModule,
                homeViewModelModule,
            )
        }
    }
}
