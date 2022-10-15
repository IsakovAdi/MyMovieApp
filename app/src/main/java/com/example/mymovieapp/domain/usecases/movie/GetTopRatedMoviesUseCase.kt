package com.example.mymovieapp.domain.usecases.movie

import com.example.mymovieapp.domain.repositories.MovieRepository


class GetTopRatedMoviesUseCase(private val repository: MovieRepository) {
    suspend fun execute(language: String, page: Int) =
        repository.getTopRatedMovies(language, page)
}