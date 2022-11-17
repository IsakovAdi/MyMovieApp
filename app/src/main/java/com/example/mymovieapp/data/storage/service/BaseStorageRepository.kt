package com.example.mymovieapp.data.storage.service

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.storage.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

abstract class BaseStorageRepository {
    suspend fun <T, K> safeStorageCall(
        mapper: Mapper<T, K>,
        storage: () -> Flow<T>,
    ): Flow<Resource<K>> {
        val response = storage.invoke().map { resource ->
            try {
                Resource.Success(mapper.mapData(resource))
            } catch (throwable: Throwable) {
                Resource.Failure(Throwable("Something went wrong"))
            }
        }
        return response
    }
}