package com.example.mymovieapp.data.cloud.mappers.movie

import com.example.mymovieapp.data.cloud.model.movie.MovieCloud
import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.domain.Mapper

class MapCloudMovieToData : Mapper<MovieCloud, MovieData> {
    override fun map(from: MovieCloud) = from.run {
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