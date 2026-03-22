package com.moviecatalog.features.login.di

import com.moviecatalog.core.navigator.RootDestination
import com.moviecatalog.core.navigator.importDestinations
import com.moviecatalog.features.login.navigation.LoginNavScreen
import org.koin.core.module.Module
import org.koin.dsl.module

public val loginFeatureModule: Module = module {
    importDestinations(RootDestination.Login to { LoginNavScreen })
}
