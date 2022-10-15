package com.example.mymovieapp.domain.usecases.movie

import com.example.mymovieapp.domain.repositories.MovieRepository


class SearchMovieUseCase(private val repository: MovieRepository) {
    suspend fun execute(language: String, query: String) =
        repository.searchMovie(language, query)
}