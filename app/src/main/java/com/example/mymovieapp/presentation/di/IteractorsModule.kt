package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.domain.GetMovieActorsUseCase
import com.example.mymovieapp.domain.GetMovieActorsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface IteractorsModule {
    @Binds
    fun bindGetMovieActorsUseCase(impl: GetMovieActorsUseCaseImpl): GetMovieActorsUseCase
}