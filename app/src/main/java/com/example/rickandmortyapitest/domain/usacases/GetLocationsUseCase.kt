package com.example.rickandmortyapitest.domain.usacases

import com.example.rickandmortyapitest.domain.repositories.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    operator fun invoke(name: String, type: String, dimension: String) =
        repository.getLocations(name, type, dimension)
}