package com.example.mymovieapp.presentation.mappers.movie

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.presentation.models.movie.MovieUi

class MapDomainMovieListToUi(
    private val mapper: Mapper<MovieDomain, MovieUi>,
) : Mapper<List<MovieDomain>, List<MovieUi>> {
    override fun map(from: List<MovieDomain>) = from.run {
        map(mapper::map)
    }
}