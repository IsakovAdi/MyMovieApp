package com.example.mymovieapp.domain.repositories.storage

import com.example.mymovieapp.domain.models.movie.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieStorageRepository {

    suspend fun saveMovie(movie: MovieModel)
    suspend fun deleteMovie(movieId: Int)
    suspend fun getMovies(): List<MovieModel>
}