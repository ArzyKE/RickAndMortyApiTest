package com.example.rickandmortyapitest.domain.usacases

import com.example.rickandmortyapitest.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetEpisodesBySearchUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend operator fun invoke(name: String) = repository.getEpisodesBySearch(name)
}