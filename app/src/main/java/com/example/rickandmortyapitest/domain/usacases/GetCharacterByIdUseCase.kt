package com.example.rickandmortyapitest.domain.usacases

import com.example.rickandmortyapitest.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(id: Int) = repository.getCharacterById(id)
}