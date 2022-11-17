package com.example.mymovieapp.data.storage.service

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovieapp.data.mappers.storage.MapMovieToStorage
import com.example.mymovieapp.data.mappers.storage.MapMoviesFromStorage
import com.example.mymovieapp.data.storage.Resource
import com.example.mymovieapp.data.storage.room.AppDatabase
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class MovieStorageRepositoryImpl(application: Application) : MovieStorageRepository,
    BaseStorageRepository() {

    private val movieDao = AppDatabase.getInstance(application).movieDao()
    private val movieMapper = MapMovieToStorage()
    private val movieListMapper = MapMoviesFromStorage()

    override suspend fun saveMovie(movie: MovieModel) {
        movieDao.saveMovie(movieMapper.mapDomain(movie))
    }

    override suspend fun deleteMovie(movieId: Int) {
        movieDao.deleteShopItem(movieId)
    }

    override suspend fun getMovies(): Flow<Resource<List<MovieModel>>> =
        safeStorageCall(mapper = movieListMapper) {
            movieDao.getMoviesList()
        }

//        safeStorageCall {
//            movieDao.getMoviesList()
//        }.let {
//            when (it) {
//                is Resource.Success -> movieListMapper.mapData(it.value)
//                is Resource.Failure -> {
//                    throw it.throwable
//                }
//            }
//
//        }
}