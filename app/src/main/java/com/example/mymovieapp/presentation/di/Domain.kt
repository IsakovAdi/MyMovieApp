package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetUpcomingMovies> {
        GetUpcomingMovies(repository = get())
    }

    factory<GetMovieDetailsUseCase> {
        GetMovieDetailsUseCase(repository = get())
    }

    factory<GetNowPlayingMovies> {
        GetNowPlayingMovies(repository = get())
    }

    factory<GetPopularMovieUseCase> {
        GetPopularMovieUseCase(repository = get())
    }

    factory<GetTopRatedMoviesUseCase> {
        GetTopRatedMoviesUseCase(repository = get())
    }

    factory<SearchMovieUseCase> {
        SearchMovieUseCase(repository = get())
    }

    factory<GetVideosUseCase> {
        GetVideosUseCase(repository = get())
    }

}