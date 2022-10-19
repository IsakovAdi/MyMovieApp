package com.example.mymovieapp.data.mappers.storage

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.storage.model.Movie
import com.example.mymovieapp.domain.models.movie.MovieModel

class MapMovieToStorage : Mapper<Movie, MovieModel>(
    fromDomain = { movie ->
        Movie(
            id = movie.id,
            posterPath = movie.posterPath,
            adult = movie.adult,
            overview = movie.overview,
            releaseDate = movie.releaseDate,
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