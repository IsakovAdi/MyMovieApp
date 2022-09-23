package com.example.mymovieapp.data

import com.example.mymovieapp.data.entities.Movie
import com.example.mymovieapp.data.entities.Movies
import com.example.mymovieapp.domain.models.MovieModel
import com.example.mymovieapp.domain.models.MoviesModel

class MovieMapper {
    fun mapEntityToMovieModel(movie: Movie): MovieModel = MovieModel(
        posterPath = movie.posterPath,
        adult = movie.adult,
        overview = movie.overview,
        releaseDate = movie.releaseDate,
        id = movie.id,
        originalTitle = movie.originalTitle,
        originalLanguage = movie.originalLanguage,
        title = movie.title,
        backdropPath = movie.backdropPath,
        popularity = movie.popularity,
        voteCount = movie.voteCount,
        video = movie.video,
        rating = movie.rating
    )

    fun mapEntityToMoviesModel(movies: Movies): MoviesModel = MoviesModel(
        page = movies.page,
        movies = mapListEntityToMovieModel(movies.movies),
        totalPage = movies.totalPage
    )

    private fun mapListEntityToMovieModel(list: List<Movie>) = list.map {
        mapEntityToMovieModel(it)
    }

}