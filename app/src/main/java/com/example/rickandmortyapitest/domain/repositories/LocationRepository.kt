package com.example.rickandmortyapitest.domain.repositories

import com.example.rickandmortyapitest.common.resource.Resource
import com.example.rickandmortyapitest.domain.models.PagingList
import com.example.rickandmortyapitest.domain.models.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun getLocations(
        name: String,
        type: String,
        dimension: String
    ): Flow<PagingList<RickAndMorty.LocationsItem>>

    suspend fun getLocationsBySearch(name: String): Flow<Resource<List<RickAndMorty.GeneralItem>>>

    fun getLocationById(id: Int): Flow<Resource<RickAndMorty.LocationsItem>>
}