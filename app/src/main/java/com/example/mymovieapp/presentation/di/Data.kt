package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.data.network.MovieRepositoryData
import com.example.mymovieapp.data.network.MovieRepositoryDataImpl
import com.example.mymovieapp.data.network.VideoRepositoryData
import com.example.mymovieapp.data.network.VideoRepositoryDataImpl
import com.example.mymovieapp.data.repository.MovieRepositoryImpl
import com.example.mymovieapp.data.repository.VideoRepositoryImpl
import com.example.mymovieapp.domain.repository.MovieRepository
import com.example.mymovieapp.domain.repository.VideoRepository
import org.koin.dsl.module


val dataModule = module {

    single<MovieRepositoryData> {
        MovieRepositoryDataImpl()
    }

    single<MovieRepository> {
        MovieRepositoryImpl(repository = get())
    }
    single<VideoRepositoryData> {
        VideoRepositoryDataImpl()
    }

    single<VideoRepository> {
        VideoRepositoryImpl(repository = get())
    }


}