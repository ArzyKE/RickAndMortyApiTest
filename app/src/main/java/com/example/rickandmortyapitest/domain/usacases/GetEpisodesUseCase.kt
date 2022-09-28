package com.example.rickandmortyapitest.domain.usacases

import com.example.rickandmortyapitest.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    operator fun invoke(name: String, episode: String) =
        repository.getEpisodes(name, episode)
}