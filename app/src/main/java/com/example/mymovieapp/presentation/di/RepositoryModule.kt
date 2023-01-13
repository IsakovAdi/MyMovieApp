package com.example.mymovieapp.presentation.di

import android.content.Context
import com.example.mymovieapp.data.cloud.base.ResourceProvider
import com.example.mymovieapp.data.cloud.source.movie.MovieCloudDataSource
import com.example.mymovieapp.data.cloud.source.person.PersonCloudDataSource
import com.example.mymovieapp.data.cloud.source.video.VideoCloudDataSource
import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.models.movie.MovieDetailsData
import com.example.mymovieapp.data.models.movie.MoviesResponseData
import com.example.mymovieapp.data.models.person.PersonDetailsData
import com.example.mymovieapp.data.models.person.PersonsResponseData
import com.example.mymovieapp.data.models.video.VideosResponseData
import com.example.mymovieapp.data.repositoryImpl.cloud.MovieRepositoryImpl
import com.example.mymovieapp.data.repositoryImpl.cloud.PersonRepositoryImpl
import com.example.mymovieapp.data.repositoryImpl.cloud.VideoRepositoryImpl
import com.example.mymovieapp.data.repositoryImpl.storage.MovieStorageRepositoryImpl
import com.example.mymovieapp.data.storage.source.MovieStorageDataSource
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.movie.MovieDetailsDomain
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.domain.models.person.PersonsResponseDomain
import com.example.mymovieapp.domain.models.video.VideosResponseDomain
import com.example.mymovieapp.domain.repositories.network.MovieRepository
import com.example.mymovieapp.domain.repositories.network.PersonRepository
import com.example.mymovieapp.domain.repositories.network.VideoRepository
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideResourceProvider(
        @ApplicationContext context: Context,
    ): ResourceProvider = ResourceProvider.Base(context = context)

    @Provides
    fun provideMovieRepository(
        movieCloudDataSource: MovieCloudDataSource,
        mapMovieResponse: Mapper<MoviesResponseData, MoviesResponseDomain>,
        mapMovieDetails: Mapper<MovieDetailsData, MovieDetailsDomain>,
        dispatchersProvider: DispatchersProvider,
    ): MovieRepository = MovieRepositoryImpl(
        movieCloudDataSource = movieCloudDataSource,
        mapMovieResponse = mapMovieResponse,
        mapMovieDetails = mapMovieDetails,
        dispatchersProvider = dispatchersProvider)

    @Provides
    fun providePersonRepository(
        personCloudDataSource: PersonCloudDataSource,
        mapPersonResponse: Mapper<PersonsResponseData, PersonsResponseDomain>,
        mapPersonDetails: Mapper<PersonDetailsData, PersonDetailsDomain>,
        dispatchersProvider: DispatchersProvider,
    ): PersonRepository = PersonRepositoryImpl(
        personCloudDataSource = personCloudDataSource,
        mapPersonResponse = mapPersonResponse,
        mapPersonDetails = mapPersonDetails,
        dispatchersProvider = dispatchersProvider
    )

    @Provides
    fun provideVideoRepository(
        videoCloudDataSource: VideoCloudDataSource,
        mapper: Mapper<VideosResponseData, VideosResponseDomain>,
    ): VideoRepository = VideoRepositoryImpl(
        videoCloudDataSource = videoCloudDataSource,
        mapper = mapper
    )

    @Provides
    fun provideMovieStorageRepositoryImpl(
        movieStorageDataSource: MovieStorageDataSource,
        mapToData: Mapper<MovieDomain, MovieData>,
        mapFromData: Mapper<MovieData, MovieDomain>,
    ): MovieStorageRepository = MovieStorageRepositoryImpl(
        movieStorageDataSource = movieStorageDataSource,
        mapToData = mapToData,
        mapFromData = mapFromData
    )


}