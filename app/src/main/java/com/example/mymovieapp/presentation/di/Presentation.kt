package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.presentation.viewModels.MovieDetailsViewModel
import com.example.mymovieapp.presentation.viewModels.MovieFragmentViewModel
import com.example.mymovieapp.presentation.viewModels.PersonsViewModel
import com.example.mymovieapp.presentation.viewModels.SearchMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentation = module {
    viewModel<MovieFragmentViewModel> {
        MovieFragmentViewModel(
            getPopularMovieUseCase = get(),
            getNowPlayingMovies = get(),
            getUpcomingMovies = get(),
            getTopRatedMoviesUseCase = get(),
            getLanguageUseCase = get(),
            changeLanguageUseCase = get()
        )
    }

    viewModel<MovieDetailsViewModel> {
        MovieDetailsViewModel(
            getMovieDetailsUseCase = get(),
            getPersonDetailsUseCase = get(),
            getSimilarMoviesUseCase = get(),
            getRecommendMoviesUseCase = get(),
            getLanguageUseCase = get()
        )
    }

    viewModel<PersonsViewModel> {
        PersonsViewModel(
            getPersonsUseCase = get(),
            getLanguageUseCase = get()
        )
    }

    viewModel<SearchMoviesViewModel> {
        SearchMoviesViewModel(searchMovieUseCase = get())
    }

}

 