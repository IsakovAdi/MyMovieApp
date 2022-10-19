package com.example.mymovieapp.data.mappers.network.movie

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.movie.MovieDetails
import com.example.mymovieapp.data.timeFormat
import com.example.mymovieapp.domain.models.movie.MovieDetailsModel

class MovieDetailsMapper : Mapper<MovieDetails, MovieDetailsModel>(
    fromData = { movie ->
        MovieDetailsModel(
            backdrop_path = movie.backdrop_path,
            budget = movie.budget,
            genres = movie.genres.map {
                it.name
            },
            id = movie.id,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            runtime = timeFormat(movie.runtime),
            status = movie.status,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
        )
    }
)