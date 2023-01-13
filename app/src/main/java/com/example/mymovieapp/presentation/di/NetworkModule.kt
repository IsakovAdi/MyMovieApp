package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.data.cloud.api.MovieApi
import com.example.mymovieapp.data.cloud.api.PersonApi
import com.example.mymovieapp.data.cloud.api.VideoApi
import com.example.mymovieapp.data.cloud.provide.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideMakeService(
        retrofitBuilder: ProvideRetrofitBuilder,
    ): MakeService = MakeServiceImpl(retrofitBuilder = retrofitBuilder)

    @Provides
    fun provideMovieService(
        makeService: MakeService,
    ): MovieApi = makeService.service(MovieApi::class.java)

    @Provides
    fun providePersonService(
        makeService: MakeService,
    ): PersonApi = makeService.service(PersonApi::class.java)

    @Provides

    fun provideVideoService(
        makeService: MakeService,
    ): VideoApi = makeService.service(VideoApi::class.java)

    @Provides
    fun provideProvideConverterFactory(): ProvideConverterFactory = ProvideConverterFactoryImpl()

    @Provides
    fun provideProvideInterceptorDebug(): ProvideInterceptor = ProvideInterceptorImpl.Debug()

    @Provides
    fun provideProvideOkHttpClientBuilder(
        provideInterceptor: ProvideInterceptor,
    ): ProvideOkHttpClientBuilder =
        ProvideOkHttpClientBuilderImpl(provideInterceptor = provideInterceptor)

    @Provides
    fun provideProvideRetrofitBuilder(
        provideConverterFactory: ProvideConverterFactory,
        provideOkHttpClientBuilder: ProvideOkHttpClientBuilder,
    ): ProvideRetrofitBuilder = ProvideRetrofitBuilderImpl(
        provideConverterFactory = provideConverterFactory,
        provideOkHttpClientBuilder = provideOkHttpClientBuilder
    )
}