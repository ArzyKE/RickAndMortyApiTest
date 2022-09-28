package com.example.rickandmortyapitest.presentation.ui.fragments.character.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapitest.base.BaseViewModel
import com.example.rickandmortyapitest.domain.models.RickAndMorty
import com.example.rickandmortyapitest.domain.usacases.GetCharacterByIdUseCase
import com.example.rickandmortyapitest.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterId: GetCharacterByIdUseCase
) : BaseViewModel() {

    private val _characterDetailState = MutableLiveData<UIState<RickAndMorty.CharactersItem>>()
    val characterDetailState: LiveData<UIState<RickAndMorty.CharactersItem>> = _characterDetailState

    fun getCharacterById(id: Int) = viewModelScope.launch {
        subscribeTo(_characterDetailState) {
            getCharacterId(id)
        }
    }
}