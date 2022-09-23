package com.example.mymovieapp.domain.usecases

import com.example.mymovieapp.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val repository: MovieRepository) {
    suspend fun execute(movieId: Int, language: String) =
        repository.getMovieDetails(movieId, language)
}