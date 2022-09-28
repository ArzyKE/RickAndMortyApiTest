package com.example.rickandmortyapitest.presentation.ui.fragments.episode.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapitest.base.BaseViewModel
import com.example.rickandmortyapitest.domain.models.RickAndMorty
import com.example.rickandmortyapitest.domain.usacases.GetEpisodeByIdUseCase
import com.example.rickandmortyapitest.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodeId: GetEpisodeByIdUseCase
) : BaseViewModel() {

    private val _episodeDetailState = MutableLiveData<UIState<RickAndMorty.EpisodesItem>>()
    val episodeDetailState: LiveData<UIState<RickAndMorty.EpisodesItem>> = _episodeDetailState

    fun getEpisodeById(id: Int) = viewModelScope.launch {
        subscribeTo(_episodeDetailState) {
            getEpisodeId(id)
        }
    }
}