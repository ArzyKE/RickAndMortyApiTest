package com.example.rickandmortyapitest.presentation.ui.fragments.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmortyapitest.base.BaseViewModel
import com.example.rickandmortyapitest.domain.models.RickAndMorty
import com.example.rickandmortyapitest.domain.usacases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetCharactersUseCase,
) : BaseViewModel() {

    private val _charactersState = MutableLiveData<PagingData<RickAndMorty.CharactersItem>>()
    val charactersState: LiveData<PagingData<RickAndMorty.CharactersItem>> = _charactersState

    private val _charactersFilterState = MutableLiveData<PagingData<RickAndMorty.CharactersItem>>()
    val charactersFilterState: LiveData<PagingData<RickAndMorty.CharactersItem>> =
        _charactersFilterState

    fun getCharacters(name: String) {
        viewModelScope.launch {
            getAllCharacters(name, status = "", gender = "").collect {
                _charactersState.value = it as PagingData<RickAndMorty.CharactersItem>
            }
        }
    }

    fun getCharactersWithFilter(name: String, status: String, gender: String) {
        viewModelScope.launch {
            getAllCharacters(name, status, gender).collect {
                _charactersFilterState.value = it as PagingData<RickAndMorty.CharactersItem>
            }
        }
    }
}