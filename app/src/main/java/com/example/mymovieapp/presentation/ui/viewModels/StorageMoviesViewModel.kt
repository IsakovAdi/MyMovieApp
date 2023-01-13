package com.example.mymovieapp.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.cloud.base.ResourceProvider
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import com.example.mymovieapp.presentation.models.movie.MovieUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageMoviesViewModel @Inject constructor(
    private val repository: MovieStorageRepository,
    private val mapMovieFromDomain: Mapper<List<MovieDomain>, List<MovieUi>>,
    private val resourceProvider: ResourceProvider,
    private val dispatchersProvider: DispatchersProvider,
) : ViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    val storageMovies = repository.getAllMoviesFromDatabase()
        .map(mapMovieFromDomain::map)
        .flowOn(dispatchersProvider.default())
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteMovie(movieId: Int) =
        viewModelScope.launch {
            repository.deleteMovieFromDatabase(movieId = movieId)
        }


}
