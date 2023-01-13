package com.example.mymovieapp.presentation.mappers.movie

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.models.movie.MoviesResponseUi

class MapDomainMovieResponseToUi(
    private val mapper: Mapper<List<MovieDomain>, List<MovieUi>>,
) : Mapper<MoviesResponseDomain, MoviesResponseUi> {
    override fun map(from: MoviesResponseDomain) = from.run {
        MoviesResponseUi(
            page = page,
            movies = mapper.map(movies),
            totalPage = totalPage
        )
    }
}