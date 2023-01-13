package com.example.mymovieapp.data.storage.source

import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.storage.db.MovieDao
import com.example.mymovieapp.data.storage.model.MovieStorage
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieStorageDataSourceImpl(
    private val movieDao: MovieDao,
    private val mapFromStorage: Mapper<MovieStorage, MovieData>,
    private val mapToStorage: Mapper<MovieData, MovieStorage>,
    private val dispatchersProvider: DispatchersProvider,
) : MovieStorageDataSource {
    override  fun fetchAllMoviesFromDatabase(): Flow<List<MovieData>> =
        movieDao.getMoviesList()
            .flowOn(dispatchersProvider.io())
            .map(::mapMovieStorageToData)
            .flowOn(dispatchersProvider.default())

    private fun mapMovieStorageToData(movies: List<MovieStorage>) = movies.map(mapFromStorage::map)

    override suspend fun saveMovieToDatabase(movie: MovieData) =
        withContext(dispatchersProvider.io()) {
            movieDao.saveMovie(movie = mapToStorage.map(movie))
        }

    override suspend fun deleteMovieFromDatabase(movieId: Int) =
        withContext(dispatchersProvider.io()) {
            movieDao.deleteMovie(movieId = movieId)
        }
}