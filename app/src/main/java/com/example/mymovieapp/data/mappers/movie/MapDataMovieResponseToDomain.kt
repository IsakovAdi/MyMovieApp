package com.example.mymovieapp.data.mappers.movie

import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.models.movie.MoviesResponseData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain

class MapDataMovieResponseToDomain(
    private val mapper: Mapper<List<MovieData>, List<MovieDomain>>,
) : Mapper<MoviesResponseData, MoviesResponseDomain> {
    override fun map(from: MoviesResponseData) = from.run {
        MoviesResponseDomain(
            page = page,
            movies = mapper.map(movies),
            totalPage = totalPage
        )
    }
}