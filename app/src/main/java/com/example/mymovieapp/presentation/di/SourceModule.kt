package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.data.cloud.api.MovieApi
import com.example.mymovieapp.data.cloud.api.PersonApi
import com.example.mymovieapp.data.cloud.api.VideoApi
import com.example.mymovieapp.data.cloud.model.movie.MovieDetailsCloud
import com.example.mymovieapp.data.cloud.model.movie.MoviesResponseCloud
import com.example.mymovieapp.data.cloud.model.person.PersonDetailsCloud
import com.example.mymovieapp.data.cloud.model.person.PersonsResponseCloud
import com.example.mymovieapp.data.cloud.model.video.VideosResponseCloud
import com.example.mymovieapp.data.cloud.source.ResponseHandler
import com.example.mymovieapp.data.cloud.source.ResponseHandlerImpl
import com.example.mymovieapp.data.cloud.source.movie.MovieCloudDataSource
import com.example.mymovieapp.data.cloud.source.movie.MovieCloudDataSourceImpl
import com.example.mymovieapp.data.cloud.source.person.PersonCloudDataSource
import com.example.mymovieapp.data.cloud.source.person.PersonCloudDataSourceImpl
import com.example.mymovieapp.data.cloud.source.video.VideoCloudDataSource
import com.example.mymovieapp.data.cloud.source.video.VideoCloudDataSourceImpl
import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.models.movie.MovieDetailsData
import com.example.mymovieapp.data.models.movie.MoviesResponseData
import com.example.mymovieapp.data.models.person.PersonDetailsData
import com.example.mymovieapp.data.models.person.PersonsResponseData
import com.example.mymovieapp.data.models.video.VideosResponseData
import com.example.mymovieapp.data.storage.db.MovieDao
import com.example.mymovieapp.data.storage.model.MovieStorage
import com.example.mymovieapp.data.storage.source.MovieStorageDataSource
import com.example.mymovieapp.data.storage.source.MovieStorageDataSourceImpl
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    fun provideResponseHandlerImpl(
        dispatchersProvider: DispatchersProvider,
    ): ResponseHandler = ResponseHandlerImpl(dispatchersProvider = dispatchersProvider)

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider = DispatchersProvider.Base()

    @Provides
    fun provideMovieCloudDataSource(
        api: MovieApi,
        movieResponseMapper: Mapper<MoviesResponseCloud, MoviesResponseData>,
        movieDetailsMapper: Mapper<MovieDetailsCloud, MovieDetailsData>,
        responseHandler: ResponseHandler,
        dispatchersProvider: DispatchersProvider,
    ): MovieCloudDataSource = MovieCloudDataSourceImpl(
        api = api,
        movieResponseMapper = movieResponseMapper,
        movieDetailsMapper = movieDetailsMapper,
        responseHandler = responseHandler,
        dispatchersProvider = dispatchersProvider
    )

    @Provides
    fun providePersonCloudDataSource(
        api: PersonApi,
        personResponseMapper: Mapper<PersonsResponseCloud, PersonsResponseData>,
        personDetailsMapper: Mapper<PersonDetailsCloud, PersonDetailsData>,
        responseHandler: ResponseHandler,
        dispatchersProvider: DispatchersProvider,
    ): PersonCloudDataSource = PersonCloudDataSourceImpl(
        api = api,
        personResponseMapper = personResponseMapper,
        personDetailsMapper = personDetailsMapper,
        responseHandler = responseHandler,
        dispatchersProvider = dispatchersProvider
    )

    @Provides
    fun provideVideoCloudDataSourceImpl(
        api: VideoApi,
        mapper: Mapper<VideosResponseCloud, VideosResponseData>,
        dispatchersProvider: DispatchersProvider,
    ): VideoCloudDataSource = VideoCloudDataSourceImpl(
        api = api,
        mapper = mapper,
        dispatchersProvider = dispatchersProvider
    )

    @Provides
    fun provideMovieStorageDataSource(
        movieDao: MovieDao,
        mapFromStorage: Mapper<MovieStorage, MovieData>,
        mapToStorage: Mapper<MovieData, MovieStorage>,
        dispatchersProvider: DispatchersProvider,
    ): MovieStorageDataSource = MovieStorageDataSourceImpl(
        movieDao = movieDao,
        mapFromStorage = mapFromStorage,
        mapToStorage = mapToStorage,
        dispatchersProvider = dispatchersProvider
    )

}