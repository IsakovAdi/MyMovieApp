package com.example.mymovieapp.domain.models.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieGenresModel(
    val id: Int,
    val name: String,
) : Parcelable