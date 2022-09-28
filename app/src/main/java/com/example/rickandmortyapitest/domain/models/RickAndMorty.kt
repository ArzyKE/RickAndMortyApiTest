package com.example.rickandmortyapitest.domain.models

import com.example.rickandmortyapitest.base.IBaseDiffModel
import com.example.rickandmortyapitest.domain.models.character.Location
import com.example.rickandmortyapitest.domain.models.character.Origin

sealed class RickAndMorty : IBaseDiffModel {

    data class CharactersItem(
        val image: String?,
        val gender: String?,
        val species: String?,
        val created: String?,
        val origin: Origin?,
        val name: String?,
        val location: Location?,
        val episode: List<String>?,
        override val id: Int?,
        val type: String?,
        val url: String?,
        val status: String?
    ) : RickAndMorty()

    data class EpisodesItem(
        val airDate: String?,
        val characters: List<String>?,
        val created: String?,
        val episode: String?,
        val name: String?,
        override val id: Int?,
        val url: String?
    ) : RickAndMorty()

    data class LocationsItem(
        val created: String?,
        val name: String?,
        val residents: List<String>?,
        override val id: Int?,
        val type: String?,
        val dimension: String?,
        val url: String?
    ) : RickAndMorty()

    data class GeneralItem(
        val image: String?,
        val gender: String?,
        val species: String?,
        val created: String?,
        val origin: Origin?,
        val name: String?,
        val location: Location?,
        override val id: Int?,
        val type: String?,
        val status: String?,
        val airDate: String?,
        val characters: List<String>?,
        val episode: String?,
        val residents: List<String>?,
        val dimension: String?,
        val url: String?
    ) : RickAndMorty()
}