package com.example.mymovieapp.domain.usecases.movie

import com.example.mymovieapp.domain.repositories.MovieRepository

class GetSimilarMoviesUseCase(private val repository: MovieRepository) {
    suspend fun execute(movieId: Int, language: String) =
        repository.getSimilarMovies(movieId, language)
}