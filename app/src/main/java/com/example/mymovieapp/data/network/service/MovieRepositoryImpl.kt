package com.example.mymovieapp.data.network.service

import com.example.mymovieapp.data.network.Resource
import com.example.mymovieapp.data.api.MovieApi
import com.example.mymovieapp.data.mappers.network.movie.MovieDetailsMapper
import com.example.mymovieapp.data.mappers.network.movie.MoviesMapper
import com.example.mymovieapp.data.network.Utils
import com.example.mymovieapp.domain.models.movie.MovieDetailsModel
import com.example.mymovieapp.domain.models.movie.MoviesModel
import com.example.mymovieapp.domain.repositories.network.MovieRepository

class MovieRepositoryImpl(
    private val movieApi: MovieApi
) : MovieRepository, BaseRepository() {
    private val moviesMapperToModel = MoviesMapper()
    private val movieMapper = MovieDetailsMapper()

    override suspend fun getPopularMovie(language: String, page: Int): MoviesModel =
        safeApiCall {
            movieApi.getPopular(Utils.API_KEY, language, page)
        }.let { response ->
            when (response) {
                is Resource.Success -> moviesMapperToModel.mapData(response.value)
                is Resource.Failure -> {
                    throw  response.serviceException
                }
            }
        }

    override suspend fun getTopRatedMovies(language: String, page: Int): MoviesModel =
        safeApiCall {
            movieApi.getTops(Utils.API_KEY, language, page)
        }.let { response ->
            when (response) {
                is Resource.Success -> moviesMapperToModel.mapData(response.value)
                is Resource.Failure -> {
                    throw  response.serviceException
                }
            }
        }

    override suspend fun getUpcomingMovies(language: String, page: Int): MoviesModel =
        safeApiCall {
            movieApi.getUpcoming(Utils.API_KEY, language, page)
        }.let { response ->
            when (response) {
                is Resource.Success -> moviesMapperToModel.mapData(response.value)
                is Resource.Failure -> {
                    throw  response.serviceException
                }
            }
        }

    override suspend fun getNowPlayingMovies(language: String, page: Int): MoviesModel =
        safeApiCall {
            movieApi.getNowPlaying(Utils.API_KEY, language, page)
        }.let { response ->
            when (response) {
                is Resource.Success -> moviesMapperToModel.mapData(response.value)
                is Resource.Failure -> {
                    throw  response.serviceException
                }
            }
        }

    override suspend fun getMovieDetails(movieId: Int, language: String): MovieDetailsModel =
        safeApiCall {
            movieApi.getMovieDetails(movieId, Utils.API_KEY, language)
        }.let { response ->
            when (response) {
                is Resource.Success -> {
                    movieMapper.mapData(response.value)
                }
                is Resource.Failure -> {
                    throw  response.serviceException
                }
            }
        }

    override suspend fun searchMovie(language: String, query: String): MoviesModel =
        safeApiCall {
            movieApi.search(Utils.API_KEY, language, query)
        }.let { response ->
            when (response) {
                is Resource.Success -> moviesMapperToModel.mapData(response.value)
                is Resource.Failure -> {
                    throw response.serviceException
                }
            }
        }



    override suspend fun getSimilarMovies(movieId: Int, language: String): MoviesModel =
        safeApiCall {
            movieApi.getSimilarMovies(movieId, Utils.API_KEY, language, 1)
        }.let { response ->
            when (response) {
                is Resource.Success -> moviesMapperToModel.mapData(response.value)
                is Resource.Failure -> {
                    throw  response.serviceException
                }
            }
        }

    override suspend fun getRecommendMovies(
        movieId: Int,
        language: String
    ): MoviesModel =
        safeApiCall {
            movieApi.getRecommendMovies(movieId, Utils.API_KEY, language, 1)
        }.let { response ->
            when (response) {
                is Resource.Success -> moviesMapperToModel.mapData(response.value)
                is Resource.Failure -> {
                    throw response.serviceException
                }
            }
        }

}