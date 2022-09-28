package com.example.rickandmortyapitest.data.network.dtos.models

import com.example.rickandmortyapitest.data.network.dtos.models.character.LocationDto
import com.example.rickandmortyapitest.data.network.dtos.models.character.OriginDto
import com.google.gson.annotations.SerializedName

sealed class RickAndMortyDto {

    data class CharactersItem(

        @SerializedName("image")
        val image: String?,

        @SerializedName("gender")
        val gender: String?,

        @SerializedName("species")
        val species: String?,

        @SerializedName("created")
        val created: String?,

        @SerializedName("origin")
        val origin: OriginDto?,

        @SerializedName("name")
        val name: String?,

        @SerializedName("location")
        val location: LocationDto?,

        @SerializedName("episode")
        val episode: List<String>?,

        @SerializedName("id")
        val id: Int?,

        @SerializedName("type")
        val type: String?,

        @SerializedName("url")
        val url: String?,

        @SerializedName("status")
        val status: String?
    ) : RickAndMortyDto()

    data class EpisodesItem(

        @SerializedName("air_date")
        val airDate: String?,

        @SerializedName("characters")
        val characters: List<String>?,

        @SerializedName("created")
        val created: String?,

        @SerializedName("episode")
        val episode: String?,

        @SerializedName("name")
        val name: String?,

        @SerializedName("id")
        val id: Int?,

        @SerializedName("url")
        val url: String?
    ) : RickAndMortyDto()

    data class LocationsItem(

        @SerializedName("created")
        val created: String?,

        @SerializedName("name")
        val name: String?,

        @SerializedName("residents")
        val residents: List<String>?,

        @SerializedName("id")
        val id: Int?,

        @SerializedName("type")
        val type: String?,

        @SerializedName("dimension")
        val dimension: String?,

        @SerializedName("url")
        val url: String?
    ) : RickAndMortyDto()
}
