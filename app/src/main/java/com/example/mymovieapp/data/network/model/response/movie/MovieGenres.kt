package com.example.mymovieapp.data.network.model.response.movie

import com.google.gson.annotations.SerializedName

class MovieGenres(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)