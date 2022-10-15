package com.example.mymovieapp.domain.models.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonDetailsModel(
    val adult: Boolean,
    val known_for_department: String,
    val deathDay: String?,
    val id: Int,
    val name: String,
    val also_known_as: List<String>,
    val gender: Int,
    val biography: String,
    val popularity: Double,
    val place_of_birth: String?,
    val profile_path: String?,
    val imdb_id: String,
    val homepage: String?
) : Parcelable