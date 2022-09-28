package com.example.rickandmortyapitest.data.network.apiservices

import com.example.rickandmortyapitest.data.network.dtos.models.ApiResponse
import com.example.rickandmortyapitest.data.network.dtos.models.RickAndMortyDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiServices {

    @GET("api/location")
    suspend fun getLocations(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("dimension") dimension: String
    ): ApiResponse<RickAndMortyDto.LocationsItem>

    @GET("api/location/{id}")
    suspend fun getLocationById(
        @Path("id") id: Int
    ): RickAndMortyDto.LocationsItem
}