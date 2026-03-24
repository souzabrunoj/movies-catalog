package com.moviecatalog.features.login.auth.di

import com.moviecatalog.features.login.auth.domain.usecase.MovieLoginUserUseCase
import com.moviecatalog.features.login.auth.ui.uiModel.MovieLoginUiModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

public val authModule: Module = module {
    factory { MovieLoginUserUseCase(get()) }
    viewModel { MovieLoginUiModel(loginUser = get()) }
}
