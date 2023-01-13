package com.example.mymovieapp.domain.repositories.storage

import com.example.mymovieapp.domain.models.movie.MovieDomain
import kotlinx.coroutines.flow.Flow


interface MovieStorageRepository {
    suspend  fun saveMovieToDatabase(movie: MovieDomain)
    suspend fun deleteMovieFromDatabase(movieId: Int)
    fun getAllMoviesFromDatabase(): Flow<List<MovieDomain>>
}