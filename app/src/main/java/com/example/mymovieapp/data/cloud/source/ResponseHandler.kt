package com.example.mymovieapp.data.cloud.source

import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.Mapper
import retrofit2.Response

interface ResponseHandler {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataRequestState<T>
    suspend fun <T, K> safeApiMapperCall(
        mapper: Mapper<T, K>,
        apiCall: suspend () -> Response<T>,
    ): DataRequestState<K>
}