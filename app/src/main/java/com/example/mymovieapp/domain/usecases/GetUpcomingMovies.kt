package com.example.mymovieapp.domain.usecases

import com.example.mymovieapp.domain.repository.MovieRepository

class GetUpcomingMovies(private val repository: MovieRepository) {
    suspend fun execute(language:String, page:Int) =
        repository.getUpcomingMovies(language, page)
}