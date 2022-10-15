package com.example.mymovieapp.domain.usecases.movie

import com.example.mymovieapp.domain.repositories.MovieRepository


class GetNowPlayingMovies(private val repository: MovieRepository) {
    suspend fun execute(language: String, page: Int) =
        repository.getNowPlayingMovies(language, page)
}