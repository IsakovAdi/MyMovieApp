package com.example.mymovieapp.data.network.service

import com.example.mymovieapp.data.Resource
import com.example.mymovieapp.data.isMobileOnline
import com.example.mymovieapp.data.network.model.error.ServiceException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(
                            serviceException = try {
                                val jsonObj =
                                    JSONObject(throwable.response()?.errorBody()?.string() ?: "")
                                ServiceException(
                                    errorMessage = jsonObj["message"].toString(),
                                )
                            } catch (e: Exception) {
                                ServiceException()
                            }
                        )
                    }
                    else -> {
                        if (isMobileOnline())
                            Resource.Failure(
                                serviceException = ServiceException(
                                    errorMessage = throwable.localizedMessage
                                )
                            )
                        else
                            Resource.Failure(
                                serviceException = ServiceException(
                                    title = "Your internet connection is lost.",
                                    errorMessage = "Please, restore it and go back to the app"
                                )
                            )
                    }
                }
            }
        }
    }
}
