package com.example.mymovieapp.data.mappers.storage

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.storage.model.Movie
import com.example.mymovieapp.domain.models.movie.MovieModel

class MapMoviesFromStorage : Mapper<List<Movie>, List<MovieModel>>(
    fromData = {
        it.map { movie ->
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
    }
)