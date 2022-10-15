package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.data.network.RetrofitInstance
import com.example.mymovieapp.domain.repositories.MovieRepository
import com.example.mymovieapp.data.network.service.MovieRepositoryImpl
import com.example.mymovieapp.data.network.service.PersonRepositoryImpl
import com.example.mymovieapp.data.network.service.VideoRepositoryImpl
import com.example.mymovieapp.data.storage.LanguageRepositoryImpl
import com.example.mymovieapp.domain.repositories.LanguageRepository
import com.example.mymovieapp.domain.repositories.PersonRepository
import com.example.mymovieapp.domain.repositories.VideoRepository
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

}