package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.presentation.fragments.moviesF.MovieFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movies = module {
    viewModel<MovieFragmentViewModel> {
        MovieFragmentViewModel(
            getPopularMovieUseCase = get(),
            getNowPlayingMovies = get(),
            getUpcomingMovies = get(),
            getTopRatedMoviesUseCase = get(),
            searchMovieUseCase = get()
        )
    }
}

 