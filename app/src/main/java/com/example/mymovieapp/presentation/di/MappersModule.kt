package com.example.mymovieapp.presentation.di

import com.example.mymovieapp.data.cloud.mappers.movie.MapCloudMovieDetailsToData
import com.example.mymovieapp.data.cloud.mappers.movie.MapCloudMovieListToData
import com.example.mymovieapp.data.cloud.mappers.movie.MapCloudMovieResponseToData
import com.example.mymovieapp.data.cloud.mappers.movie.MapCloudMovieToData
import com.example.mymovieapp.data.cloud.mappers.person.MapCloudPersonDetailsToData
import com.example.mymovieapp.data.cloud.mappers.person.MapCloudPersonListToData
import com.example.mymovieapp.data.cloud.mappers.person.MapCloudPersonResponseToData
import com.example.mymovieapp.data.cloud.mappers.person.MapCloudPersonToData
import com.example.mymovieapp.data.cloud.mappers.video.MapCloudVideoListToData
import com.example.mymovieapp.data.cloud.mappers.video.MapCloudVideoResponseToData
import com.example.mymovieapp.data.cloud.mappers.video.MapCloudVideoToData
import com.example.mymovieapp.data.cloud.model.movie.MovieCloud
import com.example.mymovieapp.data.cloud.model.movie.MovieDetailsCloud
import com.example.mymovieapp.data.cloud.model.movie.MoviesResponseCloud
import com.example.mymovieapp.data.cloud.model.person.PersonCloud
import com.example.mymovieapp.data.cloud.model.person.PersonDetailsCloud
import com.example.mymovieapp.data.cloud.model.person.PersonsResponseCloud
import com.example.mymovieapp.data.cloud.model.video.VideoCloud
import com.example.mymovieapp.data.cloud.model.video.VideosResponseCloud
import com.example.mymovieapp.data.mappers.movie.*
import com.example.mymovieapp.data.mappers.person.MapDataPersonDetailsToDomain
import com.example.mymovieapp.data.mappers.person.MapDataPersonListToDomain
import com.example.mymovieapp.data.mappers.person.MapDataPersonResponseToDomain
import com.example.mymovieapp.data.mappers.person.MapDataPersonToDomain
import com.example.mymovieapp.data.mappers.video.MapDataVideoListToDomain
import com.example.mymovieapp.data.mappers.video.MapDataVideoResponseToDomain
import com.example.mymovieapp.data.mappers.video.MapDataVideoToDomain
import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.models.movie.MovieDetailsData
import com.example.mymovieapp.data.models.movie.MoviesResponseData
import com.example.mymovieapp.data.models.person.PersonData
import com.example.mymovieapp.data.models.person.PersonDetailsData
import com.example.mymovieapp.data.models.person.PersonsResponseData
import com.example.mymovieapp.data.models.video.VideoData
import com.example.mymovieapp.data.models.video.VideosResponseData
import com.example.mymovieapp.data.storage.mappers.MapDataMovieToStorage
import com.example.mymovieapp.data.storage.mappers.MapStorageMovieListToData
import com.example.mymovieapp.data.storage.mappers.MapStorageMovieToData
import com.example.mymovieapp.data.storage.model.MovieStorage
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDetailsDomain
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.domain.models.person.PersonDomain
import com.example.mymovieapp.domain.models.person.PersonsResponseDomain
import com.example.mymovieapp.domain.models.video.VideoDomain
import com.example.mymovieapp.domain.models.video.VideosResponseDomain
import com.example.mymovieapp.presentation.mappers.movie.*
import com.example.mymovieapp.presentation.mappers.person.*
import com.example.mymovieapp.presentation.mappers.video.MapDomainVideoListToUi
import com.example.mymovieapp.presentation.mappers.video.MapDomainVideoResponseToUi
import com.example.mymovieapp.presentation.mappers.video.MapDomainVideoToUi
import com.example.mymovieapp.presentation.models.movie.MovieDetailsUi
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.models.movie.MoviesResponseUi
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi
import com.example.mymovieapp.presentation.models.person.PersonUi
import com.example.mymovieapp.presentation.models.person.PersonsResponseUi
import com.example.mymovieapp.presentation.models.video.VideoUi
import com.example.mymovieapp.presentation.models.video.VideosResponseUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class MappersModule {

    @Provides
    fun provideMapCloudMovieDetailsToData():
            Mapper<MovieDetailsCloud, MovieDetailsData> = MapCloudMovieDetailsToData()

    @Provides
    fun provideMapCloudMovieListToData(mapper: Mapper<MovieCloud, MovieData>):
            Mapper<List<MovieCloud>, List<MovieData>> = MapCloudMovieListToData(mapper = mapper)

    @Provides
    fun provideMapCloudMovieResponseToData(mapper: Mapper<List<MovieCloud>, List<MovieData>>):
            Mapper<MoviesResponseCloud, MoviesResponseData> =
        MapCloudMovieResponseToData(mapper = mapper)

    @Provides
    fun provideMapCloudMovieToData(): Mapper<MovieCloud, MovieData> = MapCloudMovieToData()

    @Provides
    fun provideMapCloudPersonDetailsToData(): Mapper<PersonDetailsCloud, PersonDetailsData> =
        MapCloudPersonDetailsToData()

    @Provides
    fun provideMapCloudPersonListToData(mapper: Mapper<PersonCloud, PersonData>):
            Mapper<List<PersonCloud>, List<PersonData>> = MapCloudPersonListToData(mapper = mapper)

    @Provides
    fun provideMapCloudPersonResponseToData(mapper: Mapper<List<PersonCloud>, List<PersonData>>):
            Mapper<PersonsResponseCloud, PersonsResponseData> =
        MapCloudPersonResponseToData(mapper = mapper)

    @Provides
    fun provideMapCloudPersonToData(mapper: Mapper<List<MovieCloud>, List<MovieData>>):
            Mapper<PersonCloud, PersonData> = MapCloudPersonToData(mapper = mapper)

    @Provides
    fun provideMapCloudVideoListToData(mapper: Mapper<VideoCloud, VideoData>):
            Mapper<List<VideoCloud>, List<VideoData>> = MapCloudVideoListToData(mapper = mapper)

    @Provides
    fun provideMapCloudVideoResponseToData(mapper: Mapper<List<VideoCloud>, List<VideoData>>):
            Mapper<VideosResponseCloud, VideosResponseData> =
        MapCloudVideoResponseToData(mapper = mapper)

    @Provides
    fun provideMapCloudVideoToData(): Mapper<VideoCloud, VideoData> = MapCloudVideoToData()

    @Provides
    fun provideMapDataMovieDetailsToDomain(): Mapper<MovieDetailsData, MovieDetailsDomain> =
        MapDataMovieDetailsToDomain()

    @Provides
    fun provideMapDataMovieListToDomain(mapper: Mapper<MovieData, MovieDomain>):
            Mapper<List<MovieData>, List<MovieDomain>> = MapDataMovieListToDomain(mapper = mapper)

    @Provides
    fun provideMapDataMovieResponseToDomain(
        mapper: Mapper<List<MovieData>, List<MovieDomain>>,
    ): Mapper<MoviesResponseData, MoviesResponseDomain> =
        MapDataMovieResponseToDomain(mapper = mapper)

    @Provides
    fun provideMapDataMovieToDomain(): Mapper<MovieData, MovieDomain> = MapDataMovieToDomain()

    @Provides
    fun provideMapDomainMovieToData(): Mapper<MovieDomain, MovieData> = MapDomainMovieToData()

    @Provides
    fun provideMapDataPersonDetailsToDomain(): Mapper<PersonDetailsData, PersonDetailsDomain> =
        MapDataPersonDetailsToDomain()

    @Provides
    fun provideMapDataPersonListToDomain(
        mapper: Mapper<PersonData, PersonDomain>,
    ): Mapper<List<PersonData>, List<PersonDomain>> =
        MapDataPersonListToDomain(mapper = mapper)

    @Provides
    fun provideMapDataPersonResponseToDomain(
        mapper: Mapper<List<PersonData>, List<PersonDomain>>,
    ): Mapper<PersonsResponseData, PersonsResponseDomain> =
        MapDataPersonResponseToDomain(mapper = mapper)

    @Provides
    fun provideMapDataPersonToDomain(
        mapper: Mapper<List<MovieData>, List<MovieDomain>>,
    ): Mapper<PersonData, PersonDomain> =
        MapDataPersonToDomain(mapper = mapper)


    @Provides
    fun provideMapDataVideoListToDomain(
        mapper: Mapper<VideoData, VideoDomain>,
    ): Mapper<List<VideoData>, List<VideoDomain>> =
        MapDataVideoListToDomain(mapper = mapper)

    @Provides
    fun provideMapDataVideoResponseToDomain(
        mapper: Mapper<List<VideoData>, List<VideoDomain>>,
    ): Mapper<VideosResponseData, VideosResponseDomain> =
        MapDataVideoResponseToDomain(mapper = mapper)

    @Provides
    fun provideMapDataVideoToDomain(): Mapper<VideoData, VideoDomain> = MapDataVideoToDomain()

    @Provides
    fun provideMapDataMovieToStorage(): Mapper<MovieData, MovieStorage> = MapDataMovieToStorage()

    @Provides
    fun provideMapStorageMovieListToData(
        mapper: Mapper<MovieStorage, MovieData>,
    ): Mapper<List<MovieStorage>, List<MovieData>> = MapStorageMovieListToData(mapper = mapper)

    @Provides
    fun provideMapStorageMovieToData(): Mapper<MovieStorage, MovieData> = MapStorageMovieToData()

    @Provides
    fun provideMapDomainMovieDetailsToUi(): Mapper<MovieDetailsDomain, MovieDetailsUi> =
        MapDomainMovieDetailsToUi()

    @Provides
    fun provideMapDomainMovieListToUi(
        mapper: Mapper<MovieDomain, MovieUi>,
    ): Mapper<List<MovieDomain>, List<MovieUi>> =
        MapDomainMovieListToUi(mapper = mapper)

    @Provides
    fun provideMapDomainMovieResponseToUi(
        mapper: Mapper<List<MovieDomain>, List<MovieUi>>,
    ): Mapper<MoviesResponseDomain, MoviesResponseUi> =
        MapDomainMovieResponseToUi(mapper = mapper)

    @Provides
    fun provideMapDomainMovieToUi(): Mapper<MovieDomain, MovieUi> = MapDomainMovieToUi()

    @Provides
    fun provideMapUiMovieToDomain(): Mapper<MovieUi, MovieDomain> = MapUiMovieToDomain()

    @Provides
    fun provideMapDomainPersonDetailsToUi(): Mapper<PersonDetailsDomain, PersonDetailsUi> =
        MapDomainPersonDetailsToUi()

    @Provides
    fun provideMapDomainPersonListToUi(
        mapper: Mapper<PersonDomain, PersonUi>,
    ): Mapper<List<PersonDomain>, List<PersonUi>> = MapDomainPersonListToUi(mapper = mapper)

    @Provides
    fun provideMapDomainPersonResponseToUi(
        mapper: Mapper<List<PersonDomain>, List<PersonUi>>,
    ): Mapper<PersonsResponseDomain, PersonsResponseUi> =
        MapDomainPersonResponseToUi(mapper = mapper)

    @Provides
    fun provideMapDomainPersonToUi(
        mapper: Mapper<List<MovieDomain>, List<MovieUi>>,
    ): Mapper<PersonDomain, PersonUi> = MapDomainPersonToUi(mapper = mapper)

    @Provides
    fun provideMapDomainVideoListToUi(
        mapper: Mapper<VideoDomain, VideoUi>,
    ): Mapper<List<VideoDomain>, List<VideoUi>> = MapDomainVideoListToUi(mapper = mapper)

    @Provides
    fun provideMapDomainVideoResponseToUi(
        mapper: Mapper<List<VideoDomain>, List<VideoUi>>,
    ): Mapper<VideosResponseDomain, VideosResponseUi> = MapDomainVideoResponseToUi(mapper = mapper)

    @Provides
    fun provideMapDomainVideoToUi(): Mapper<VideoDomain, VideoUi> = MapDomainVideoToUi()

    @Provides
    fun provideMapDomainPersonDetailsListToUi(
        mapper: Mapper<PersonDetailsDomain, PersonDetailsUi>,
    ): Mapper<List<PersonDetailsDomain>, List<PersonDetailsUi>> =
        MapDomainPersonDetailsListToUi(mapper = mapper)

}