package com.moviecatalog.features.home.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

public class MuseumRepository(
    private val museumApi: MuseumApi,
    private val museumStorage: MuseumStorage,
) {
    private val scope = CoroutineScope(SupervisorJob())

    public fun initialize() {
        scope.launch {
            refresh()
        }
    }

    public suspend fun refresh() {
        museumStorage.saveObjects(museumApi.getData())
    }

    public fun getObjects(): Flow<List<MuseumObject>> = museumStorage.getObjects()

    public fun getObjectById(objectId: Int): Flow<MuseumObject?> = museumStorage.getObjectById(objectId)
}
