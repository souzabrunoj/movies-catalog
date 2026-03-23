package com.moviecatalog.features.login.di

import com.moviecatalog.core.navigator.LoginDestination
import com.moviecatalog.core.navigator.importDestinations
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.features.login.navigation.MovieCatalogLoginStep
import org.koin.core.module.Module
import org.koin.dsl.module

private val loginScreenFactories: Map<LoginDestination, () -> Step> = mapOf(
    LoginDestination.Login to { MovieCatalogLoginStep },
)

public val loginFeatureModule: Module = module {
    importDestinations(loginScreenFactories)
}
