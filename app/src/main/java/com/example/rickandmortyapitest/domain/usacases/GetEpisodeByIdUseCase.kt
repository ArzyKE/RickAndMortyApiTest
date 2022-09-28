package com.example.rickandmortyapitest.domain.usacases

import com.example.rickandmortyapitest.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    operator fun invoke(id: Int) = repository.getEpisodeById(id)
}