package com.example.mymovieapp.domain.usecases.movie.storage

import com.example.mymovieapp.data.storage.Resource
import com.example.mymovieapp.domain.error.ErrorHandler
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class GetStorageMoviesUseCase(
    private val repository: MovieStorageRepository,
    private val errorHandler: ErrorHandler,
) {
    suspend fun execute(): Flow<List<MovieModel>> = flow {
        val response = repository.getMovies()
        response.collect { state ->
            when (state) {
                is Resource.Success -> this.emit(state.value?: listOf())
                is Resource.Failure -> errorHandler.handle(state.throwable)
            }
        }
    }
}