package com.example.mymovieapp.domain.usecases.movie.network

import com.example.mymovieapp.domain.repositories.network.MovieRepository


class GetNowPlayingMovies(private val repository: MovieRepository) {
    suspend fun execute(language: String, page: Int) =
        repository.getNowPlayingMovies(language, page)
}