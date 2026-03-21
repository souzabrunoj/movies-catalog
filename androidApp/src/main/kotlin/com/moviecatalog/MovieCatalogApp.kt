package com.moviecatalog

import android.app.Application
import com.moviecatalog.di.initKoin

class MovieCatalogApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
