package com.example.mymovieapp.domain.usecases.movie.network

import com.example.mymovieapp.domain.repositories.network.MovieRepository

class GetMovieDetailsUseCase(private val repository: MovieRepository) {
    suspend fun execute(movieId: Int, language: String) =
        repository.getMovieDetails(movieId, language)
}