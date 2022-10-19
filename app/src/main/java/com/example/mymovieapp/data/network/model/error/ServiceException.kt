package com.example.mymovieapp.data.network.model.error

data class ServiceException(
    val title: String? = "Something went wrong",
    val errorMessage: String? = ""
) : Throwable(errorMessage)