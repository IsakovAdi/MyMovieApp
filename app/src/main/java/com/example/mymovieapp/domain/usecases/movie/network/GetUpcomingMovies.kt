package com.example.mymovieapp.domain.usecases.movie.network

import com.example.mymovieapp.domain.repositories.network.MovieRepository


class GetUpcomingMovies(private val repository: MovieRepository) {
    suspend fun execute(language:String, page:Int) =
        repository.getUpcomingMovies(language, page)
}