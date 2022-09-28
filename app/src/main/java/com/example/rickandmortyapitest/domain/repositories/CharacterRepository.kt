package com.example.rickandmortyapitest.domain.repositories

import com.example.rickandmortyapitest.common.resource.Resource
import com.example.rickandmortyapitest.domain.models.PagingList
import com.example.rickandmortyapitest.domain.models.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacters(
        name: String,
        status: String,
        gender: String
    ): Flow<PagingList<RickAndMorty.CharactersItem>>

    suspend fun getCharactersBySearch(name: String): Flow<Resource<List<RickAndMorty.GeneralItem>>>

    fun getCharacterById(id: Int): Flow<Resource<RickAndMorty.CharactersItem>>
}