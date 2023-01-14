package com.example.mymovieapp.presentation.ui.viewModels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.cloud.base.ResourceProvider
import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.domain.repositories.network.MovieRepository
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import com.example.mymovieapp.domain.takeSuccess
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.models.movie.MoviesResponseUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val storageRepository: MovieStorageRepository,
    private val dispatchersProvider: DispatchersProvider,
    private val mapMovieResponse: Mapper<MoviesResponseDomain, MoviesResponseUi>,
    private val mapMovieToDomain: Mapper<MovieUi, MovieDomain>,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    val movies = createMutableSharedFlowAsLiveData<MoviesResponseUi>()

//    val movies = searchKeyword.flatMapLatest {
//        repository.searchMovie(it)
//    }.map(mapMovieResponse::map)
//        .flowOn(dispatchersProvider.default())
//        .catch { throwable: Throwable ->
//            _error.emit(resourceProvider.handleException(throwable))
//        }
//        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    //    val movies = searchMovieKeyword.map(repository::searchMovie)
//        .map { it.map(mapMovieResponse) }
//
    fun searchMovie(query: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.searchMovie(query)
        }.onSuccess {
            if (it.takeSuccess() != null) {
                movies.emit(mapMovieResponse.map(it.takeSuccess()!!))
            }
        }.onFailure {
            _error.emit(resourceProvider.handleException(it))
        }
    }

    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.saveMovieToDatabase(mapMovieToDomain.map(movie))
    }
}