package com.example.rickandmortyapitest.domain.repositories

import com.example.rickandmortyapitest.common.resource.Resource
import com.example.rickandmortyapitest.domain.models.PagingList
import com.example.rickandmortyapitest.domain.models.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    fun getEpisodes(
        name: String,
        episode: String
    ): Flow<PagingList<RickAndMorty.EpisodesItem>>

    suspend fun getEpisodesBySearch(name: String): Flow<Resource<List<RickAndMorty.GeneralItem>>>

    fun getEpisodeById(id: Int): Flow<Resource<RickAndMorty.EpisodesItem>>
}