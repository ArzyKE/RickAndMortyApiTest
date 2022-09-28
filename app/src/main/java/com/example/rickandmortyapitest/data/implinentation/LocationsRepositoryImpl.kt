package com.example.rickandmortyapitest.data.implinentation

import com.example.rickandmortyapitest.base.BaseRepository
import com.example.rickandmortyapitest.data.implinentation.pagingsources.LocationsPagingSource
import com.example.rickandmortyapitest.data.network.apiservices.LocationApiServices
import com.example.rickandmortyapitest.data.network.dtos.models.toGeneral
import com.example.rickandmortyapitest.data.network.dtos.models.toLocations
import com.example.rickandmortyapitest.domain.repositories.LocationRepository
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val service: LocationApiServices
) : BaseRepository(), LocationRepository {

    override fun getLocations(name: String, type: String, dimension: String) =
        doPagingRequest(LocationsPagingSource(service, name, type, dimension))

    override suspend fun getLocationsBySearch(name: String) = doRequest {
        service.getLocations(1, name, "", "").results.map {
            it.toGeneral()
        }
    }

    override fun getLocationById(id: Int) = doRequest {
        service.getLocationById(id).toLocations()
    }
}