package com.example.mymovieapp.data.mappers.movie

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.movie.MovieDetails
import com.example.mymovieapp.data.network.model.response.movie.MovieGenres
import com.example.mymovieapp.domain.models.movie.MovieDetailsModel

class MovieDetailsMapper : Mapper<MovieDetails, MovieDetailsModel>(
    fromData = { movie ->
        MovieDetailsModel(
            adult = movie.adult,
            backdrop_path = movie.backdrop_path,
            budget = movie.budget,
            genres = movie.genres.map {
                MovieGenres(
                    id = it.id,
                    name = it.name
                )
            },
            homepage = movie.homepage,
            id = movie.id,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            revenue = movie.revenue,
            runtime = movie.runtime,
            status = movie.status,
            tagline = movie.tagline,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
        )
    }
)