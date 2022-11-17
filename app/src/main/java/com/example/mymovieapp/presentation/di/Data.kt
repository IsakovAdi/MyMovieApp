package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.data.network.RetrofitInstance
import com.example.mymovieapp.domain.repositories.network.MovieRepository
import com.example.mymovieapp.data.network.service.MovieRepositoryImpl
import com.example.mymovieapp.data.network.service.PersonRepositoryImpl
import com.example.mymovieapp.data.network.service.VideoRepositoryImpl
import com.example.mymovieapp.data.storage.service.LanguageRepositoryImpl
import com.example.mymovieapp.data.storage.service.MovieStorageRepositoryImpl
import com.example.mymovieapp.domain.error.ErrorHandler
import com.example.mymovieapp.domain.repositories.storage.LanguageRepository
import com.example.mymovieapp.domain.repositories.network.PersonRepository
import com.example.mymovieapp.domain.repositories.network.VideoRepository
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import org.koin.dsl.module


val dataModule = module {

    single<MovieRepository> {
        MovieRepositoryImpl(movieApi = RetrofitInstance.movieApi)
    }

    single<VideoRepository> {
        VideoRepositoryImpl(api = RetrofitInstance.videoApi)
    }

    single<PersonRepository> {
        PersonRepositoryImpl(api = RetrofitInstance.personApi)
    }

    single<LanguageRepository> {
        LanguageRepositoryImpl(context = get())
    }

    single<MovieStorageRepository> {
        MovieStorageRepositoryImpl(application = get())
    }

    single<ErrorHandler> {
        ErrorHandler.Base(context = get())
    }

}