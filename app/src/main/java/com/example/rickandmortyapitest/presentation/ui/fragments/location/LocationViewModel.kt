package com.example.rickandmortyapitest.presentation.ui.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmortyapitest.base.BaseViewModel
import com.example.rickandmortyapitest.domain.models.RickAndMorty
import com.example.rickandmortyapitest.domain.usacases.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val getAllLocations: GetLocationsUseCase
) : BaseViewModel() {

    private val _locationsState = MutableLiveData<PagingData<RickAndMorty.LocationsItem>>()
    val locationsState: LiveData<PagingData<RickAndMorty.LocationsItem>> = _locationsState

    private val _locationsFilterState = MutableLiveData<PagingData<RickAndMorty.LocationsItem>>()
    val locationsFilterSate: LiveData<PagingData<RickAndMorty.LocationsItem>> =
        _locationsFilterState

    fun getLocations(name: String) {
        viewModelScope.launch {
            getAllLocations(name, type = "", dimension = "").collect {
                _locationsState.value = it as PagingData<RickAndMorty.LocationsItem>
            }
        }
    }

    fun getLocationsWithFilter(name: String, type: String, dimension: String) {
        viewModelScope.launch {
            getAllLocations(name, type, dimension).collect {
                _locationsFilterState.value = it as PagingData<RickAndMorty.LocationsItem>
            }
        }
    }
}