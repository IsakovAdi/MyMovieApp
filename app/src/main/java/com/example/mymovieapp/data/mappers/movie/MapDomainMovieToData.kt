package com.example.mymovieapp.data.mappers.movie

import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDomain

class MapDomainMovieToData : Mapper<MovieDomain, MovieData> {
    override fun map(from: MovieDomain) = from.run {
        MovieData(
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