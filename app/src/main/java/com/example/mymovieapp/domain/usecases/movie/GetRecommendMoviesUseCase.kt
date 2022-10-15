package com.example.mymovieapp.domain.usecases.movie

import com.example.mymovieapp.domain.repositories.MovieRepository

class GetRecommendMoviesUseCase(private val repository: MovieRepository) {
    suspend fun execute(movieId: Int, language: String) =
        repository.getRecommendMovies(movieId, language)
}