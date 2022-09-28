package com.example.rickandmortyapitest.domain.usacases

import com.example.rickandmortyapitest.domain.repositories.LocationRepository
import javax.inject.Inject

class GetLocationByIdUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    operator fun invoke(id: Int) = repository.getLocationById(id)
}