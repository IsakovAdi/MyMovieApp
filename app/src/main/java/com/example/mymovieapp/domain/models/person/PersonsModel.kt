package com.example.mymovieapp.domain.models.person

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonsModel(
    val page: Int,
    val persons: List<PersonModel>,
    val total_results: Int,
    val total_pages: Int
) : Parcelable
