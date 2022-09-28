package com.example.rickandmortyapitest.domain.usacases

import com.example.rickandmortyapitest.domain.repositories.LocationRepository
import javax.inject.Inject

class GetLocationsBySearchUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(name: String) = repository.getLocationsBySearch(name)
}