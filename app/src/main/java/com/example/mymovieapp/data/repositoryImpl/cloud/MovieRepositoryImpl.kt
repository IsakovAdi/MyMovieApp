package com.example.mymovieapp.data.repositoryImpl.cloud

import com.example.mymovieapp.data.cloud.source.movie.MovieCloudDataSource
import com.example.mymovieapp.data.models.movie.MovieDetailsData
import com.example.mymovieapp.data.models.movie.MoviesResponseData
import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.movie.MovieDetailsDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.domain.repositories.network.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val movieCloudDataSource: MovieCloudDataSource,
    private val mapMovieResponse: Mapper<MoviesResponseData, MoviesResponseDomain>,
    private val mapMovieDetails: Mapper<MovieDetailsData, MovieDetailsDomain>,
    private val dispatchersProvider: DispatchersProvider,
) : MovieRepository {
    override fun getPopularMovie(page: Int): Flow<MoviesResponseDomain> =
        movieCloudDataSource.getPopularMovie(page = page)
            .map(mapMovieResponse::map)
            .flowOn(dispatchersProvider.default())

    override fun getTopRatedMovies(page: Int): Flow<MoviesResponseDomain> =
        movieCloudDataSource.getTopRatedMovies(page = page)
            .map(mapMovieResponse::map)
            .flowOn(dispatchersProvider.default())

    override fun getUpcomingMovies(page: Int): Flow<MoviesResponseDomain> =
        movieCloudDataSource.getUpcomingMovies(page = page)
            .map(mapMovieResponse::map)
            .flowOn(dispatchersProvider.default())

    override fun getNowPlayingMovies(page: Int): Flow<MoviesResponseDomain> =
        movieCloudDataSource.getNowPlayingMovies(page = page)
            .map(mapMovieResponse::map)
            .flowOn(dispatchersProvider.default())

//    override fun searchMovie(query: String?): Flow<MoviesResponseDomain> =
//        movieCloudDataSource.searchMovie(query = query)
//            .map(mapMovieResponse::map)
//            .flowOn(dispatchersProvider.default())
    override suspend fun searchMovie(query: String?): DataRequestState<MoviesResponseDomain> =
        movieCloudDataSource.searchMovie(query = query)
            .map(mapMovieResponse)


    override fun getSimilarMovies(movieId: Int): Flow<MoviesResponseDomain> =
        movieCloudDataSource.getSimilarMovies(movieId = movieId)
            .map(mapMovieResponse::map)
            .flowOn(dispatchersProvider.default())

    override fun getRecommendMovies(movieId: Int): Flow<MoviesResponseDomain> =
        movieCloudDataSource.getRecommendMovies(movieId = movieId)
            .map(mapMovieResponse::map)
            .flowOn(dispatchersProvider.default())

    override suspend fun getMovieDetails(movieId: Int): DataRequestState<MovieDetailsDomain> =
        movieCloudDataSource.getMovieDetails(movieId = movieId)
            .map(mapMovieDetails)

}