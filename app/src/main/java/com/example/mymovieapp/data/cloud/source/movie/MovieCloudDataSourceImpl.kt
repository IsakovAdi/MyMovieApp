package com.example.mymovieapp.data.cloud.source.movie

import com.example.mymovieapp.data.cloud.api.MovieApi
import com.example.mymovieapp.data.cloud.model.movie.MovieDetailsCloud
import com.example.mymovieapp.data.cloud.model.movie.MoviesResponseCloud
import com.example.mymovieapp.data.cloud.source.ResponseHandler
import com.example.mymovieapp.data.models.movie.MovieDetailsData
import com.example.mymovieapp.data.models.movie.MoviesResponseData
import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieCloudDataSourceImpl(
    private val api: MovieApi,
    private val movieResponseMapper: Mapper<MoviesResponseCloud, MoviesResponseData>,
    private val movieDetailsMapper: Mapper<MovieDetailsCloud, MovieDetailsData>,
    private val responseHandler: ResponseHandler,
    private val dispatchersProvider: DispatchersProvider,
) : MovieCloudDataSource {

    override fun getPopularMovie(page: Int): Flow<MoviesResponseData> = flow {
        emit(api.getPopularMovies(page = page))
    }.flowOn(dispatchersProvider.io()).map { it.body()!! }.map(movieResponseMapper::map)
        .flowOn(dispatchersProvider.default())

    override fun getTopRatedMovies(
        page: Int,
    ): Flow<MoviesResponseData> = flow {
        emit(api.getTopRatingMovies(page = page))
    }.flowOn(dispatchersProvider.io()).map { it.body()!! }.map(movieResponseMapper::map)
        .flowOn(dispatchersProvider.default())

    override fun getUpcomingMovies(
        page: Int,
    ): Flow<MoviesResponseData> = flow {
        emit(api.getUpcomingMovies(page = page))
    }.flowOn(dispatchersProvider.io()).map { it.body()!! }.map(movieResponseMapper::map)
        .flowOn(dispatchersProvider.default())

    override fun getNowPlayingMovies(
        page: Int,
    ): Flow<MoviesResponseData> =
        flow {
            emit(api.getNowPlayingMovies(page = page))
        }.flowOn(dispatchersProvider.io()).map { it.body()!! }.map(movieResponseMapper::map)
            .flowOn(dispatchersProvider.default())

//    override fun searchMovie(
//        query: String?,
//    ): Flow<MoviesResponseData> =
//        flow {
//            emit(api.searchMovie(query = query))
//        }.flowOn(dispatchersProvider.io()).map { it.body()!! }.map(movieResponseMapper::map)
//            .flowOn(dispatchersProvider.default())
    override suspend fun searchMovie(
        query: String?,
    ): DataRequestState<MoviesResponseData> =
        responseHandler.safeApiMapperCall(movieResponseMapper) {
            api.searchMovie(query = query)
        }

    override fun getSimilarMovies(
        movieId: Int,
    ): Flow<MoviesResponseData> =
        flow {
            emit(api.getSimilarMovies(movieId = movieId))
        }.flowOn(dispatchersProvider.io()).map { it.body()!! }.map(movieResponseMapper::map)
            .flowOn(dispatchersProvider.default())

    override fun getRecommendMovies(
        movieId: Int,
    ): Flow<MoviesResponseData> =
        flow {
            emit(api.getRecommendMovies(movieId = movieId))
        }.flowOn(dispatchersProvider.io()).map { it.body()!! }.map(movieResponseMapper::map)

    override suspend fun getMovieDetails(
        movieId: Int,
    ): DataRequestState<MovieDetailsData> = responseHandler.safeApiMapperCall(movieDetailsMapper) {
        api.getMovieDetails(movieId = movieId)
    }

}