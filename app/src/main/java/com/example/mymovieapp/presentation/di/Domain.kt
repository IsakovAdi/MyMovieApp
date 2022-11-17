package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.domain.usecases.language.ChangeLanguageUseCase
import com.example.mymovieapp.domain.usecases.language.GetLanguageUseCase
import com.example.mymovieapp.domain.usecases.movie.network.*
import com.example.mymovieapp.domain.usecases.movie.storage.DeleteMovieUseCase
import com.example.mymovieapp.domain.usecases.movie.storage.GetStorageMoviesUseCase
import com.example.mymovieapp.domain.usecases.movie.storage.SaveMovieUseCase
import com.example.mymovieapp.domain.usecases.person.GetPersonDetailsUseCase
import com.example.mymovieapp.domain.usecases.person.GetPersonsUseCase
import com.example.mymovieapp.domain.usecases.video.GetVideosUseCase
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

    factory<GetSimilarMoviesUseCase> {
        GetSimilarMoviesUseCase(repository = get())
    }

    factory<GetRecommendMoviesUseCase> {
        GetRecommendMoviesUseCase(repository = get())
    }

    factory<SearchMovieUseCase> {
        SearchMovieUseCase(repository = get())
    }

    factory<GetVideosUseCase> {
        GetVideosUseCase(repository = get())
    }

    factory<GetPersonsUseCase> {
        GetPersonsUseCase(repository = get())
    }

    factory<GetPersonDetailsUseCase> {
        GetPersonDetailsUseCase(repository = get())
    }

    factory<GetLanguageUseCase> {
        GetLanguageUseCase(repository = get())
    }
    factory<ChangeLanguageUseCase> {
        ChangeLanguageUseCase(repository = get())
    }

    factory<DeleteMovieUseCase> {
        DeleteMovieUseCase(repository = get())
    }

    factory<GetStorageMoviesUseCase> {
        GetStorageMoviesUseCase(repository = get(), errorHandler = get())
    }

    factory<SaveMovieUseCase> {
        SaveMovieUseCase(repository = get())
    }

}