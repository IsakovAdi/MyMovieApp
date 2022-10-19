package com.example.mymovieapp.data.network

import com.example.mymovieapp.data.network.model.error.ServiceException

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(val serviceException: ServiceException) : Resource<Nothing>()
}
