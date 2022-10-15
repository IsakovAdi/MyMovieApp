package com.example.mymovieapp.data.network.model.response.person

import com.example.mymovieapp.data.network.model.response.movie.Movie
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Person(
    @SerializedName("profile_path") val profile_path: String?,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("known_for") val known_for: List<Movie>,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Double
)
