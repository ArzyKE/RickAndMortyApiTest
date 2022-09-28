package com.example.rickandmortyapitest.base

interface IBaseDiffModel {
    val id: Int?
    override fun equals(other: Any?): Boolean
}