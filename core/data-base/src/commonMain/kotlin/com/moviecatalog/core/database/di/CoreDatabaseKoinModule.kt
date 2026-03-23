package com.moviecatalog.core.database.di

import org.koin.core.module.Module


public val coreDatabaseKoinModule: Module
    get() = databasePlatformModule
