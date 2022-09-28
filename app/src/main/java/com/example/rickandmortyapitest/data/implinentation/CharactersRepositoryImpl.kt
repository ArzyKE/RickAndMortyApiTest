package com.example.rickandmortyapitest.data.implinentation

import com.example.rickandmortyapitest.base.BaseRepository
import com.example.rickandmortyapitest.data.implinentation.pagingsources.CharactersPagingSource
import com.example.rickandmortyapitest.data.network.apiservices.CharacterApiServices
import com.example.rickandmortyapitest.data.network.dtos.models.toCharacters
import com.example.rickandmortyapitest.data.network.dtos.models.toGeneral
import com.example.rickandmortyapitest.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val service: CharacterApiServices
) : BaseRepository(), CharacterRepository {

    override fun getCharacters(name: String, status: String, gender: String) =
        doPagingRequest(CharactersPagingSource(service, name, status, gender))

    override suspend fun getCharactersBySearch(name: String) = doRequest {
        service.getCharacters(1, name, "", "").results.map {
            it.toGeneral()
        }
    }

    override fun getCharacterById(id: Int) = doRequest {
        service.getCharacterById(id).toCharacters()
    }
}