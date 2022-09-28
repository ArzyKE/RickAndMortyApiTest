package com.example.rickandmortyapitest.data.implinentation.pagingsources

import com.example.rickandmortyapitest.base.BasePagingSource
import com.example.rickandmortyapitest.data.network.apiservices.CharacterApiServices
import com.example.rickandmortyapitest.data.network.dtos.models.RickAndMortyDto
import com.example.rickandmortyapitest.data.network.dtos.models.toCharacters
import com.example.rickandmortyapitest.domain.models.RickAndMorty

class CharactersPagingSource(
    private val service: CharacterApiServices,
    private val name: String,
    private val status: String,
    private val gender: String
) : BasePagingSource<RickAndMortyDto.CharactersItem, RickAndMorty.CharactersItem>(
    { service.getCharacters(it, name, status, gender) },
    { data -> data.map { it.toCharacters() } }
)