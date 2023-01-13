package com.example.mymovieapp.data.cloud.model.person

import com.example.mymovieapp.data.cloud.model.movie.MovieCloud
import com.google.gson.annotations.SerializedName

data class PersonCloud(
    @SerializedName("profile_path") val profile_path: String?,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("known_for") val known_for: List<MovieCloud>,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Double,
)
