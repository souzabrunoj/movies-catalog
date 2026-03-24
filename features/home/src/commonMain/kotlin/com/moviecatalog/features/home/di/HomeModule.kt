package com.moviecatalog.features.home.di

import com.moviecatalog.features.home.details.di.detailsFeatureModule
import com.moviecatalog.features.home.list.di.listFeatureModule
import org.koin.core.module.Module
import org.koin.dsl.module

public val homeFeatureModule: Module = module {
    includes(listFeatureModule, detailsFeatureModule)
}
