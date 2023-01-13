package com.example.mymovieapp.data.repositoryImpl.storage

import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.storage.source.MovieStorageDataSource
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieStorageRepositoryImpl(
    private val movieStorageDataSource: MovieStorageDataSource,
    private val mapToData: Mapper<MovieDomain, MovieData>,
    private val mapFromData: Mapper<MovieData, MovieDomain>,
) : MovieStorageRepository {
    override suspend fun saveMovieToDatabase(movie: MovieDomain) =
        movieStorageDataSource.saveMovieToDatabase(movie = mapToData.map(movie))

    override suspend fun deleteMovieFromDatabase(movieId: Int) =
        movieStorageDataSource.deleteMovieFromDatabase(movieId = movieId)

    override fun getAllMoviesFromDatabase(): Flow<List<MovieDomain>> =
        movieStorageDataSource.fetchAllMoviesFromDatabase().map { movies ->
            movies.map(mapFromData::map)
        }
}