package com.moviecatalog.screens.detail

import androidx.lifecycle.ViewModel
import com.moviecatalog.data.MuseumObject
import com.moviecatalog.data.MuseumRepository
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}
