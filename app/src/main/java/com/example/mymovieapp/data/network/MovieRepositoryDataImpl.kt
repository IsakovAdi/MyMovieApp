package com.example.mymovieapp.data.network

import com.example.mymovieapp.data.Utils
import com.example.mymovieapp.data.entities.Movie
import com.example.mymovieapp.data.entities.Movies
import retrofit2.Response

class MovieRepositoryDataImpl:MovieRepositoryData {

    override suspend fun getPopularMovie(language: String, page: Int): Response<Movies> {
        return RetrofitInstance.movieApi.getPopular(Utils.API_KEY, language, page)
    }

    override suspend fun getTopRatedMovies(language: String, page: Int): Response<Movies> {
        return RetrofitInstance.movieApi.getTops(Utils.API_KEY, language, page)
    }

    override suspend fun getUpcomingMovies(language: String, page: Int): Response<Movies> {
        return RetrofitInstance.movieApi.getUpcoming(Utils.API_KEY, language, page)
    }

    override suspend fun getNowPlayingMovies(language: String, page: Int): Response<Movies> {
        return RetrofitInstance.movieApi.getNowPlaying(Utils.API_KEY, language, page)
    }

    override suspend fun searchMovie(language: String, query: String): Response<Movies> {
        return RetrofitInstance.movieApi.search(Utils.API_KEY, language, query)
    }

    override suspend fun getMovieDetails(movieId: Int, language: String): Response<Movie> {
        return RetrofitInstance.movieApi.getMovieDetails(movieId, Utils.API_KEY,language)
    }

}