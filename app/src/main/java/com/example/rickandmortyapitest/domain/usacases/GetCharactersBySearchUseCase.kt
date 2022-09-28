package com.example.rickandmortyapitest.domain.usacases

import com.example.rickandmortyapitest.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharactersBySearchUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(name: String) = repository.getCharactersBySearch(name)
}