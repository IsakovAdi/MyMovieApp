package com.example.mymovieapp.data.storage.service

import com.example.mymovieapp.data.storage.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseStorageRepository {
    suspend fun <T> safeStorageCall(
        storage: suspend () -> T,
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(storage.invoke())
            } catch (throwable: Throwable) {
                Resource.Failure(Throwable("Something went wrong"))
            }
        }
    }
}