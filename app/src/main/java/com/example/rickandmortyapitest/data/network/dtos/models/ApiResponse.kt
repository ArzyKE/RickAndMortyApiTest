package com.example.rickandmortyapitest.data.network.dtos.models

import android.icu.text.IDNA
import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(

    @SerializedName("results")
    val results: ArrayList<T>,

    @SerializedName("info")
    val info: Info
)