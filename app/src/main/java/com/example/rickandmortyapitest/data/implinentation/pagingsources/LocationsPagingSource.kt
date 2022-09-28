package com.example.rickandmortyapitest.data.implinentation.pagingsources

import com.example.rickandmortyapitest.base.BasePagingSource
import com.example.rickandmortyapitest.data.network.apiservices.LocationApiServices
import com.example.rickandmortyapitest.data.network.dtos.models.RickAndMortyDto
import com.example.rickandmortyapitest.data.network.dtos.models.toLocations
import com.example.rickandmortyapitest.domain.models.RickAndMorty

class LocationsPagingSource(
    private val service: LocationApiServices,
    private val name: String,
    private val type: String,
    private val dimension: String
) :
    BasePagingSource<RickAndMortyDto.LocationsItem, RickAndMorty.LocationsItem>(
        { service.getLocations(it, name, type, dimension) },
        { data -> data.map { it.toLocations() } }
    )