package com.example.mymovieapp.domain.usecases

import com.example.mymovieapp.domain.repository.MovieRepository

class GetPopularMovieUseCase(private val repository: MovieRepository) {
    suspend fun execute(language:String, page:Int) =
        repository.getPopularMovie(language, page)
}