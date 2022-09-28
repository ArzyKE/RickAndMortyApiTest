package com.example.rickandmortyapitest.di

import com.example.rickandmortyapitest.data.implinentation.CharactersRepositoryImpl
import com.example.rickandmortyapitest.data.implinentation.EpisodesRepositoryImpl
import com.example.rickandmortyapitest.data.implinentation.LocationsRepositoryImpl
import com.example.rickandmortyapitest.data.network.apiservices.CharacterApiServices
import com.example.rickandmortyapitest.data.network.apiservices.EpisodesApiServices
import com.example.rickandmortyapitest.data.network.apiservices.LocationApiServices
import com.example.rickandmortyapitest.domain.repositories.CharacterRepository
import com.example.rickandmortyapitest.domain.repositories.EpisodesRepository
import com.example.rickandmortyapitest.domain.repositories.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideCharacterRepository(service: CharacterApiServices): CharacterRepository =
        CharactersRepositoryImpl(service)

    @Provides
    fun provideLocationsRepository(service: LocationApiServices): LocationRepository =
        LocationsRepositoryImpl(service)

    @Provides
    fun provideEpisodesRepository(service: EpisodesApiServices): EpisodesRepository =
        EpisodesRepositoryImpl(service)
}