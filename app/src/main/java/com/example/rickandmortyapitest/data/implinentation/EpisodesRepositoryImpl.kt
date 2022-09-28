package com.example.rickandmortyapitest.data.implinentation

import com.example.rickandmortyapitest.base.BaseRepository
import com.example.rickandmortyapitest.data.implinentation.pagingsources.EpisodesPagingSource
import com.example.rickandmortyapitest.data.network.apiservices.EpisodesApiServices
import com.example.rickandmortyapitest.data.network.dtos.models.toEpisodes
import com.example.rickandmortyapitest.data.network.dtos.models.toGeneral
import com.example.rickandmortyapitest.domain.repositories.EpisodesRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val service: EpisodesApiServices
) : BaseRepository(), EpisodesRepository {

    override fun getEpisodes(name: String, episode: String) =
        doPagingRequest(EpisodesPagingSource(service, name, episode))

    override suspend fun getEpisodesBySearch(name: String) = doRequest {
        service.getEpisodes(1, name, "").results.map {
            it.toGeneral()
        }
    }

    override fun getEpisodeById(id: Int) = doRequest {
        service.getEpisodeById(id).toEpisodes()
    }
}