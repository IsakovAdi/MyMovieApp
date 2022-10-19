package com.example.mymovieapp.data.mappers.network.movie

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.movie.Movies
import com.example.mymovieapp.domain.models.movie.MoviesModel

class MoviesMapper(private val movieMapper: MovieListMapper = MovieListMapper()) :
    Mapper<Movies, MoviesModel>(
        fromData = {
            MoviesModel(
                page = it.page,
                movies = movieMapper.mapData(it.movies),
                totalPage = it.totalPage
            )
        }
    )