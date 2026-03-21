package com.moviecatalog

import android.app.Application
import com.moviecatalog.di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
