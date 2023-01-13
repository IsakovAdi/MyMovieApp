package com.example.mymovieapp.presentation.mappers.movie

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDetailsDomain
import com.example.mymovieapp.presentation.models.movie.MovieDetailsUi

class MapDomainMovieDetailsToUi : Mapper<MovieDetailsDomain, MovieDetailsUi> {
    override fun map(from: MovieDetailsDomain) = from.run {
        MovieDetailsUi(
            backdrop_path = backdrop_path,
            budget = budget,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            status = status,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}