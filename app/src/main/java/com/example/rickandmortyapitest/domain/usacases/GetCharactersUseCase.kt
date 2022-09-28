package com.example.rickandmortyapitest.domain.usacases

import com.example.rickandmortyapitest.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(name: String, status: String, gender: String) =
        repository.getCharacters(name, status, gender)
}