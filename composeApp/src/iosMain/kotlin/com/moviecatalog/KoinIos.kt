package com.moviecatalog

import com.moviecatalog.core.navigator.navigatorRegistryModule
import com.moviecatalog.features.home.di.homeFeatureModule
import com.moviecatalog.features.login.di.loginFeatureModule
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin


internal fun ensureKoinStarted() {
    if (GlobalContext.getOrNull() != null) return
    startKoin {
        modules(
            navigatorRegistryModule,
            loginFeatureModule,
            homeFeatureModule,
        )
    }
}
