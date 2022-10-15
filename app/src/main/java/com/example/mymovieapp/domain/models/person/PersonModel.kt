package com.example.mymovieapp.domain.models.person

import android.os.Parcelable
import com.example.mymovieapp.domain.models.movie.MovieModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonModel(
    val profile_path: String,
    val adult: Boolean,
    val id: Int,
    val known_for: List<MovieModel>,
    val name: String,
    val popularity: Double
) : Parcelable
