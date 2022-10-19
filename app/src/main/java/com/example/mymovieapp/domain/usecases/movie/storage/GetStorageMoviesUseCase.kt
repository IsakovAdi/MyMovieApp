package com.example.mymovieapp.domain.usecases.movie.storage

import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository

class GetStorageMoviesUseCase(private val repository: MovieStorageRepository) {
    suspend fun execute() = repository.getMovies()
}