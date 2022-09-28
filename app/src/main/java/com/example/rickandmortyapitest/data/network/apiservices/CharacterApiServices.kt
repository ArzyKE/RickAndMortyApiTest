package com.example.rickandmortyapitest.data.network.apiservices

import com.example.rickandmortyapitest.data.network.dtos.models.ApiResponse
import com.example.rickandmortyapitest.data.network.dtos.models.RickAndMortyDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiServices {

    @GET("api/character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("gender") gender: String
    ): ApiResponse<RickAndMortyDto.CharactersItem>

    @GET("api/character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): RickAndMortyDto.CharactersItem
}