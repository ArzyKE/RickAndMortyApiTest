package com.example.rickandmortyapitest.data.network.dtos.models

import com.example.rickandmortyapitest.data.network.dtos.models.character.LocationDto
import com.example.rickandmortyapitest.data.network.dtos.models.character.OriginDto
import com.example.rickandmortyapitest.domain.models.RickAndMorty
import com.example.rickandmortyapitest.domain.models.character.Location
import com.example.rickandmortyapitest.domain.models.character.Origin


fun RickAndMortyDto.CharactersItem.toCharacters(): RickAndMorty.CharactersItem =
    RickAndMorty.CharactersItem(
        image,
        gender,
        species,
        created,
        origin?.toOrigin(),
        name,
        location?.toLocation(),
        episode,
        id,
        type,
        url,
        status
    )

fun OriginDto.toOrigin(): Origin =
    Origin(name, url)

fun LocationDto.toLocation(): Location =
    Location(name, url)

fun RickAndMortyDto.LocationsItem.toLocations(): RickAndMorty.LocationsItem =
    RickAndMorty.LocationsItem(
        created,
        name,
        residents,
        id,
        type,
        dimension,
        url
    )

fun RickAndMortyDto.EpisodesItem.toEpisodes(): RickAndMorty.EpisodesItem =
    RickAndMorty.EpisodesItem(
        airDate,
        characters,
        created,
        episode,
        name,
        id,
        url
    )

fun RickAndMortyDto.CharactersItem.toGeneral(): RickAndMorty.GeneralItem =
    RickAndMorty.GeneralItem(
        image,
        gender,
        species,
        created,
        origin?.toOrigin(),
        name,
        location?.toLocation(),
        id,
        type,
        status,
        airDate = null,
        characters = null,
        episode = null,
        residents = null,
        dimension = null,
        url
    )

fun RickAndMortyDto.LocationsItem.toGeneral(): RickAndMorty.GeneralItem =
    RickAndMorty.GeneralItem(
        image = null,
        gender = null,
        species = null,
        created,
        origin = null,
        name,
        location = null,
        id,
        type,
        status = null,
        airDate = null,
        characters = null,
        episode = null,
        residents,
        dimension,
        url
    )

fun RickAndMortyDto.EpisodesItem.toGeneral(): RickAndMorty.GeneralItem =
    RickAndMorty.GeneralItem(
        image = null,
        gender = null,
        species = null,
        created,
        origin = null,
        name,
        location = null,
        id,
        type = null,
        status = null,
        airDate,
        characters,
        episode,
        residents = null,
        dimension = null,
        url
    )