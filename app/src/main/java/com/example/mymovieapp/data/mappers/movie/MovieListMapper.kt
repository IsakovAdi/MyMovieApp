package com.example.mymovieapp.data.mappers.movie

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.movie.Movie
import com.example.mymovieapp.domain.models.movie.MovieModel

class MovieListMapper(private val movieMapper: MovieMapper = MovieMapper()) :
    Mapper<List<Movie>, List<MovieModel>>(
        fromData = {
            it.map { movie ->
                movieMapper.mapData(movie)
            }
        }
    )