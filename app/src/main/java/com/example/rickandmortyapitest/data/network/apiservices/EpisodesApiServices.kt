package com.example.rickandmortyapitest.data.network.apiservices

import com.example.rickandmortyapitest.data.network.dtos.models.ApiResponse
import com.example.rickandmortyapitest.data.network.dtos.models.RickAndMortyDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesApiServices {


    @GET("api/character")
    suspend fun getEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("episode") episode: String
    ): ApiResponse<RickAndMortyDto.EpisodesItem>

    @GET("api/character/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): RickAndMortyDto.EpisodesItem
}