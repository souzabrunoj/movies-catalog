package com.moviecatalog.core.database.di

import com.moviecatalog.core.database.LocalDiskStorage
import com.moviecatalog.core.database.persistence.IosLocalDiskStorage
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val databasePlatformModule: Module =
    module {
        single<LocalDiskStorage> { IosLocalDiskStorage() }
    }
