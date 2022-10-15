package com.example.mymovieapp.data.network.model.error

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ServiceException(
    val title: String? = "Something went wrong",
    val errorMessage: String? = ""
) : Throwable(errorMessage)