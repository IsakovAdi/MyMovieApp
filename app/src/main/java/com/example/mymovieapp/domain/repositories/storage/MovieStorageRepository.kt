package com.example.mymovieapp.domain.repositories.storage

import androidx.lifecycle.LiveData
import com.example.mymovieapp.data.storage.Resource
import com.example.mymovieapp.domain.models.movie.MovieModel
import kotlinx.coroutines.flow.Flow


interface MovieStorageRepository {

    suspend fun saveMovie(movie: MovieModel)
    suspend fun deleteMovie(movieId: Int)
   suspend fun getMovies(): Flow<Resource<List<MovieModel>>>
}