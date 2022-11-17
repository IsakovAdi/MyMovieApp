package com.example.mymovieapp.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.mymovieapp.presentation.viewModels.*

val presentation = module {
    viewModel<MovieFragmentViewModel> {
        MovieFragmentViewModel(
            getPopularMovieUseCase = get(),
            getNowPlayingMovies = get(),
            getUpcomingMovies = get(),
            getTopRatedMoviesUseCase = get(),
            getLanguageUseCase = get(),
            changeLanguageUseCase = get(),
            saveMovieUseCase = get()
        )
    }

    viewModel<MovieDetailsViewModel> {
        MovieDetailsViewModel(
            getMovieDetailsUseCase = get(),
            getPersonDetailsUseCase = get(),
            getSimilarMoviesUseCase = get(),
            getRecommendMoviesUseCase = get(),
            getLanguageUseCase = get(),
            saveMovieUseCase = get(),
            getVideosUseCase = get()
        )
    }

    viewModel<PersonsViewModel> {
        PersonsViewModel(
            getPersonsUseCase = get(),
            getLanguageUseCase = get()
        )
    }

    viewModel<SearchMoviesViewModel> {
        SearchMoviesViewModel(
            searchMovieUseCase = get(),
            getLanguageUseCase = get(),
            saveMovieUseCase = get()
        )
    }

    viewModel<PersonDetailsViewModel> {
        PersonDetailsViewModel(
            getPersonDetailsUseCase = get(),
            getLanguageUseCase = get(),
            saveMovieUseCase = get()
        )
    }

    viewModel<StorageMoviesViewModel> {
        StorageMoviesViewModel(
            getStorageMoviesUseCase = get(),
            deleteMovieUseCase = get()
        )
    }

}

 