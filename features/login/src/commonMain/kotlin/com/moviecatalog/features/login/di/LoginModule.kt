package com.moviecatalog.features.login.di

import com.moviecatalog.core.navigator.LoginDestination
import com.moviecatalog.core.navigator.importDestinations
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.features.login.auth.di.authModule
import com.moviecatalog.features.login.navigation.MovieCatalogLoginStep
import com.moviecatalog.features.login.signup.di.signUpModule
import com.moviecatalog.features.login.signup.ui.step.MovieCatalogSignUpStep
import org.koin.core.module.Module
import org.koin.dsl.module

private val loginScreenFactories: Map<LoginDestination, () -> Step> = mapOf(
    LoginDestination.Login to { MovieCatalogLoginStep },
    LoginDestination.SignUp to { MovieCatalogSignUpStep },
)

public val loginFeatureModule: Module = module {
    includes(signUpModule, authModule)
    importDestinations(loginScreenFactories)
}
