package com.example.mymovieapp.domain.usecases.movie.storage

import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository

class SaveMovieUseCase(private val repository: MovieStorageRepository) {
    suspend fun execute(movie: MovieModel) = repository.saveMovie(movie)
}