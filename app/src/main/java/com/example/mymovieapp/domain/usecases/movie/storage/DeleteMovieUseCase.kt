package com.example.mymovieapp.domain.usecases.movie.storage

import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository

class DeleteMovieUseCase(private val repository: MovieStorageRepository) {
    suspend fun execute(movieId: Int) = repository.deleteMovie(movieId)
}