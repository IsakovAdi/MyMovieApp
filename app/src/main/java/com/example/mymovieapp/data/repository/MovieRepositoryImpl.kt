package com.example.mymovieapp.data.repository

import com.example.mymovieapp.data.MovieMapper
import com.example.mymovieapp.data.network.MovieRepositoryData
import com.example.mymovieapp.domain.models.MovieModel
import com.example.mymovieapp.domain.models.MoviesModel
import com.example.mymovieapp.domain.repository.MovieRepository

class MovieRepositoryImpl(private val repository: MovieRepositoryData) : MovieRepository {
    private val mapper = MovieMapper()

    override suspend fun getPopularMovie(language: String, page: Int): MoviesModel? {
        val response = repository.getPopularMovie(language, page)
            return mapper.mapEntityToMoviesModel(response.body()!!)
    }

    override suspend fun getTopRatedMovies(language: String, page: Int): MoviesModel? {
        val response = repository.getTopRatedMovies(language, page)
        if (response.isSuccessful) {
            return mapper.mapEntityToMoviesModel(response.body()!!)
        }
        return null
    }

    override suspend fun getUpcomingMovies(language: String, page: Int): MoviesModel? {
        val moviesResponse = repository.getUpcomingMovies(language, page)
        if (moviesResponse.isSuccessful) {
            return mapper.mapEntityToMoviesModel(moviesResponse.body()!!)
        }
        return null
    }

    override suspend fun getNowPlayingMovies(language: String, page: Int): MoviesModel? {
        val moviesResponse = repository.getNowPlayingMovies(language, page)
        if (moviesResponse.isSuccessful) {
            return mapper.mapEntityToMoviesModel(moviesResponse.body()!!)
        }
        return null
    }

    override suspend fun searchMovie(language: String, query: String): MoviesModel? {
        val moviesResponse = repository.searchMovie(language, query)
        if (moviesResponse.isSuccessful) {
            return mapper.mapEntityToMoviesModel(moviesResponse.body()!!)
        }
        return null
    }

    override suspend fun getMovieDetails(movieId: Int, language: String): MovieModel? {
        val moviesResponse =repository.getMovieDetails(movieId, language)
        if (moviesResponse.isSuccessful) {
            return mapper.mapEntityToMovieModel(moviesResponse.body()!!)
        }
        return null
    }
}