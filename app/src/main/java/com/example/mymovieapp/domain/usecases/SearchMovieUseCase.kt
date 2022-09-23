package com.example.mymovieapp.domain.usecases

import com.example.mymovieapp.domain.repository.MovieRepository

class SearchMovieUseCase(private val repository: MovieRepository) {
    suspend fun execute(language: String, query: String) =
        repository.searchMovie(language, query)
}