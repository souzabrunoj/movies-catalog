package com.moviecatalog.features.home.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviecatalog.features.home.data.MuseumObject
import com.moviecatalog.features.home.data.MuseumRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

public class MovieCatalogHomeViewModel(museumRepository: MuseumRepository) : ViewModel() {
    public val objects: StateFlow<List<MuseumObject>> =
        museumRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
