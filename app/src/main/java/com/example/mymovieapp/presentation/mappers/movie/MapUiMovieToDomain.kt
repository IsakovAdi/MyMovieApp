package com.example.mymovieapp.presentation.mappers.movie

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.presentation.models.movie.MovieUi

class MapUiMovieToDomain : Mapper<MovieUi, MovieDomain> {
    override fun map(from: MovieUi) = from.run {
        MovieDomain(
            posterPath = posterPath,
            adult = adult,
            overview = overview,
            releaseDate = releaseDate,
            id = id,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            title = title,
            backdropPath = backdropPath,
            popularity = popularity,
            voteCount = voteCount,
            video = video,
            rating = rating,
            genre_ids = genre_ids.map { it }
        )
    }
}