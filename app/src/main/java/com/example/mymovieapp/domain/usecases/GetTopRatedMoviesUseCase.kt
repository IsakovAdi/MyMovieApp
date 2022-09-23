package com.example.mymovieapp.domain.usecases

import com.example.mymovieapp.domain.repository.MovieRepository

class GetTopRatedMoviesUseCase(private val repository: MovieRepository) {
    suspend fun execute(language: String, page: Int) =
        repository.getTopRatedMovies(language, page)
}