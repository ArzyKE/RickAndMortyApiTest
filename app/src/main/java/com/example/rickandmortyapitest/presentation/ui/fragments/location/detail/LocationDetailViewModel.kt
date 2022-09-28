package com.example.rickandmortyapitest.presentation.ui.fragments.location.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapitest.base.BaseViewModel
import com.example.rickandmortyapitest.domain.models.RickAndMorty
import com.example.rickandmortyapitest.domain.usacases.GetLocationByIdUseCase
import com.example.rickandmortyapitest.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val getLocationId: GetLocationByIdUseCase
) : BaseViewModel() {

    private val _locationDetailState = MutableLiveData<UIState<RickAndMorty.LocationsItem>>()
    val locationDetailState: LiveData<UIState<RickAndMorty.LocationsItem>> = _locationDetailState

    fun getLocationById(id: Int) = viewModelScope.launch {
        subscribeTo(_locationDetailState) {
            getLocationId(id)
        }
    }
}