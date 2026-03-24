package com.moviecatalog.core.database.di

import com.moviecatalog.core.database.LocalDiskStorage
import com.moviecatalog.core.database.persistence.AndroidLocalDiskStorage
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val databasePlatformModule: Module =
    module {
        single<LocalDiskStorage> { AndroidLocalDiskStorage(androidContext()) }
    }
