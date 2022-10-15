package com.example.mymovieapp.data.mappers.movie

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.movie.Movie
import com.example.mymovieapp.domain.models.movie.MovieModel

class MovieMapper : Mapper<Movie, MovieModel>(
    fromData = { movie ->
        MovieModel(
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
            rating = movie.rating,
            genre_ids = movie.genre_ids.map {
                it
            }
        )
    }
)