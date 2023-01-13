package com.example.mymovieapp.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.cloud.base.ResourceProvider
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.domain.repositories.network.MovieRepository
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import com.example.mymovieapp.presentation.models.ResponseState
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.models.movie.MoviesResponseUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MovieTypes {
    POPULAR,
    TOP_RATED,
    NOW_PLAYING,
    UPCOMING,
}

@HiltViewModel
class MoviesFragmentViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val dispatchersProvider: DispatchersProvider,
    private val mapMovieResponse: Mapper<MoviesResponseDomain, MoviesResponseUi>,
    private val mapMovieToDomain: Mapper<MovieUi, MovieDomain>,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()

    private val movieCategoryFlow = MutableStateFlow(MovieTypes.POPULAR)
    private val pageToResponseFlow = MutableStateFlow(value = _movieResponseState.value.page)
    private val categoryAndPageFlow =
        movieCategoryFlow.combine(pageToResponseFlow) { category, page ->
            Pair(category, page)
        }

    val movies = categoryAndPageFlow.flatMapLatest {
        when (it.first) {
            MovieTypes.POPULAR -> repository.getPopularMovie(it.second)
            MovieTypes.TOP_RATED -> repository.getTopRatedMovies(it.second)
            MovieTypes.NOW_PLAYING -> repository.getNowPlayingMovies(it.second)
            MovieTypes.UPCOMING -> repository.getUpcomingMovies(it.second)
        }
    }.map(mapMovieResponse::map)
        .flowOn(dispatchersProvider.default())
        .onEach {
            _movieResponseState.emit(
                changeResponseState(
                    page = it.page,
                    totalPage = it.totalPage)
            )
        }
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun changeMovieCategory(type: MovieTypes) {
        movieCategoryFlow.tryEmit(type)
        pageToResponseFlow.tryEmit(1)
    }

    fun nextPage() {
        pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)
    }

    fun previousPage() {
        pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)

    }

    fun saveMovie(movieUi: MovieUi) = viewModelScope.launch {
        storageRepository.saveMovieToDatabase(movie = mapMovieToDomain.map(movieUi))
    }
}