package com.moviecatalog.features.login.signup.di

import com.moviecatalog.features.login.signup.data.repository.MovieSignUpRepositoryImpl
import com.moviecatalog.features.login.signup.domain.repository.MovieSignUpRepository
import com.moviecatalog.features.login.signup.domain.usecase.MovieCheckUsernameAvailabilityUseCase
import com.moviecatalog.features.login.signup.domain.usecase.MovieEvaluatePasswordRulesUseCase
import com.moviecatalog.features.login.signup.domain.usecase.MovieRegisterUserUseCase
import com.moviecatalog.features.login.signup.domain.usecase.MovieValidateSignUpNonEmptyUseCase
import com.moviecatalog.features.login.signup.ui.uiModel.MovieSignUpUiModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

public val signUpModule: Module = module {
    single<MovieSignUpRepository> { MovieSignUpRepositoryImpl() }
    factory { MovieEvaluatePasswordRulesUseCase() }
    factory { MovieValidateSignUpNonEmptyUseCase() }
    factory { MovieCheckUsernameAvailabilityUseCase(get()) }
    factory { MovieRegisterUserUseCase(get(), get(), get(), get()) }
    viewModel { MovieSignUpUiModel(evaluatePasswordRules = get(), registerUser = get()) }
}
