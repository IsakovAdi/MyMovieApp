package com.example.mymovieapp.domain

sealed class DataRequestState<T> {
    class Success<T>(val data: T) : DataRequestState<T>()
    class Error<T>(val error: Throwable) : DataRequestState<T>()

    fun <R> map(mapper: Mapper<T, R>? = null): DataRequestState<R> = when (this) {
        is Error -> Error(this.error)
        is Success -> {
            if (mapper == null) throw IllegalStateException("Mapper should not be NULL for success result")
            else Success(
                mapper.map(this.data))
        }
    }
}

fun <T> DataRequestState<T>?.takeSuccess(): T? =
    if (this is DataRequestState.Success) data else null

fun <T> DataRequestState<T>?.takeError(): Throwable? =
    if (this is DataRequestState.Error) error else null
