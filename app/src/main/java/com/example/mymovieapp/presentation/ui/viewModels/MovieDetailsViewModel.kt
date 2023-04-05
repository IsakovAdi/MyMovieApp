package com.example.mymovieapp.presentation.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.cloud.base.ResourceProvider
import com.example.mymovieapp.domain.*
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.movie.MovieDetailsDomain
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.domain.models.video.VideosResponseDomain
import com.example.mymovieapp.domain.repositories.network.MovieRepository
import com.example.mymovieapp.domain.repositories.network.VideoRepository
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import com.example.mymovieapp.presentation.models.movie.MovieDetailsUi
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.models.movie.MoviesResponseUi
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi
import com.example.mymovieapp.presentation.models.video.VideoUi
import com.example.mymovieapp.presentation.models.video.VideosResponseUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel constructor(
    private val movieId: Int,
    private val actorsIds: List<Int>,
    private val movieRepository: MovieRepository,
    private val movieStorageRepository: MovieStorageRepository,
    private val videoRepository: VideoRepository,
    private val saveMovieFromDetailsUseCase: SaveMovieFromDetailsUseCase,
    private val getMovieActorsUseCase: GetMovieActorsUseCase,

    private val mapMovieDetails: Mapper<MovieDetailsDomain, MovieDetailsUi>,
    private val mapMovieResponse: Mapper<MoviesResponseDomain, MoviesResponseUi>,
    private val mapPersons: Mapper<List<PersonDetailsDomain>, List<PersonDetailsUi>>,
    private val mapMovieToDomain: Mapper<MovieUi, MovieDomain>,
    private val videoResponseMapper: Mapper<VideosResponseDomain, VideosResponseUi>,

    private val dispatchersProvider: DispatchersProvider,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val movieIdFlow = MutableStateFlow(movieId)
    private val personsIds = MutableStateFlow(actorsIds)

    val movieFlow = movieIdFlow.map(movieRepository::getMovieDetails)
        .map { it.map(mapMovieDetails) }

//    val persons = personsIds.flatMapLatest(getMovieActorsUseCase::invoke)
//        .map { it.map { it.takeSuccess() }.filterNotNull() }
//        .map(mapPersons::map)

    val persons = personsIds.flatMapLatest(getMovieActorsUseCase::invoke)
        .map { it -> it.mapNotNull { it.takeSuccess() } }
        .onEach {
            it.forEach {
                Log.d("MY_LOG", it.name)
            }
        }
        .map(mapPersons::map)

    @OptIn(ExperimentalCoroutinesApi::class)
    val similarMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getSimilarMovies(it)
    }.map(mapMovieResponse::map)
        .flowOn(dispatchersProvider.default())
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getRecommendMovies(it)
    }.map(mapMovieResponse::map)
        .flowOn(dispatchersProvider.default())
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val trailersFlow = movieIdFlow.flatMapLatest {
        videoRepository.getVideos(movieId = it)
    }.map(videoResponseMapper::map)
        .flowOn(dispatchersProvider.default())
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val trailer = MutableSharedFlow<VideoUi>(replay = 0)

    fun changeVideo(video: VideoUi) {
        trailer.tryEmit(video)
    }

    fun changeMovieId(movieId: Int) = movieIdFlow.tryEmit(movieId)

    fun saveMovie(movieTitle: String, movieId: Int) = viewModelScope.launch {
        saveMovieFromDetailsUseCase.saveMovie(movieTitle = movieTitle, movieId = movieId)
    }

    fun saveMovieFromRv(movieUi: MovieUi) = viewModelScope.launch {
        movieStorageRepository.saveMovieToDatabase(movie = mapMovieToDomain.map(movieUi))
    }

}