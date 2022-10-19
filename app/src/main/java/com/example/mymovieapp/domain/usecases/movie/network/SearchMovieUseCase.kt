package com.example.mymovieapp.domain.usecases.movie.network

import com.example.mymovieapp.domain.repositories.network.MovieRepository


class SearchMovieUseCase(private val repository: MovieRepository) {
    suspend fun execute(language: String, query: String) =
        repository.searchMovie(language, query)
}