package com.example.mymovieapp.domain.usecases.movie.network

import com.example.mymovieapp.domain.repositories.network.MovieRepository

class GetRecommendMoviesUseCase(private val repository: MovieRepository) {
    suspend fun execute(movieId: Int, language: String) =
        repository.getRecommendMovies(movieId, language)
}