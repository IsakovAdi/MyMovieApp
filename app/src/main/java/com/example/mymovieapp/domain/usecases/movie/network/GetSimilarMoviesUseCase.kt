package com.example.mymovieapp.domain.usecases.movie.network

import com.example.mymovieapp.domain.repositories.network.MovieRepository

class GetSimilarMoviesUseCase(private val repository: MovieRepository) {
    suspend fun execute(movieId: Int, language: String) =
        repository.getSimilarMovies(movieId, language)
}