package com.example.mymovieapp.data.storage.source

import com.example.mymovieapp.data.models.movie.MovieData
import kotlinx.coroutines.flow.Flow

interface MovieStorageDataSource {

    fun fetchAllMoviesFromDatabase(): Flow<List<MovieData>>

    suspend  fun saveMovieToDatabase(movie: MovieData)

    suspend  fun deleteMovieFromDatabase(movieId: Int)
}