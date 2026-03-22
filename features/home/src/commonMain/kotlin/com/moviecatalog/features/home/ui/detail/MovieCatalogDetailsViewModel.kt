package com.moviecatalog.features.home.ui.detail

import androidx.lifecycle.ViewModel
import com.moviecatalog.features.home.data.MuseumObject
import com.moviecatalog.features.home.data.MuseumRepository
import kotlinx.coroutines.flow.Flow

public class MovieCatalogDetailsViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    public fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}
