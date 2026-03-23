package com.moviecatalog

import android.app.Application

class MovieCatalogApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initMovieCatalogKoin()
    }
}
