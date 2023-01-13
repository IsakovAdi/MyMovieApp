package com.example.mymovieapp.data.mappers.movie

import com.example.mymovieapp.data.models.movie.MovieDetailsData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDetailsDomain

class MapDataMovieDetailsToDomain : Mapper<MovieDetailsData, MovieDetailsDomain> {
    override fun map(from: MovieDetailsData) = from.run {
        MovieDetailsDomain(
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