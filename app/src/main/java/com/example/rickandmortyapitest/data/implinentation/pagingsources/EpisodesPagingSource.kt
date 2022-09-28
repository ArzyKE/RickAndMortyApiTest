package com.example.rickandmortyapitest.data.implinentation.pagingsources

import com.example.rickandmortyapitest.base.BasePagingSource
import com.example.rickandmortyapitest.data.network.apiservices.EpisodesApiServices
import com.example.rickandmortyapitest.data.network.dtos.models.RickAndMortyDto
import com.example.rickandmortyapitest.data.network.dtos.models.toEpisodes
import com.example.rickandmortyapitest.domain.models.RickAndMorty

class EpisodesPagingSource(
    private val service: EpisodesApiServices,
    private val name: String,
    private val episode: String
) :
    BasePagingSource<RickAndMortyDto.EpisodesItem, RickAndMorty.EpisodesItem>(
        { service.getEpisodes(it, name, episode) },
        { data -> data.map { it.toEpisodes() } }
    )