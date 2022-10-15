package com.example.mymovieapp.data.network.model.response.person

import com.google.gson.annotations.SerializedName

data class PersonDetails(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("known_for_department") val known_for_department: String,
    @SerializedName("deathday") val deathDay: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("also_known_as") val also_known_as: List<String>,
    @SerializedName("gender") val gender: Int,
    @SerializedName("biography") val biography: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("place_of_birth") val place_of_birth: String?,
    @SerializedName("profile_path") val profile_path: String?,
    @SerializedName("imdb_id") val imdb_id: String,
    @SerializedName("homepage") val homepage: String?
)
