package com.moviecatalog

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.error.KoinApplicationAlreadyStartedException

class MovieCatalogApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            startKoin {
                androidContext(this@MovieCatalogApplication)
                modules(movieCatalogKoinModules())
            }
        } catch (e: KoinApplicationAlreadyStartedException) {
            e.printStackTrace()
        }
    }
}
