package com.example.mymovieapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.storage.service.LanguageRepositoryImpl
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.models.person.PersonDetailsModel
import com.example.mymovieapp.domain.models.person.PersonDetailsState
import com.example.mymovieapp.domain.usecases.language.GetLanguageUseCase
import com.example.mymovieapp.domain.usecases.movie.storage.SaveMovieUseCase
import com.example.mymovieapp.domain.usecases.person.GetPersonDetailsUseCase
import kotlinx.coroutines.launch

class PersonDetailsViewModel(
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
) : ViewModel() {
    private val _person: MutableLiveData<PersonDetailsModel> = MutableLiveData()
    val person: LiveData<PersonDetailsModel> get() = _person

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    private lateinit var _detailsState: PersonDetailsState
    val detailsState: PersonDetailsState get() = _detailsState

    private val language = getLanguageUseCase.execute()

    init {
        getDetailsState()
    }

    fun getPerson(personId: Int) = viewModelScope.launch {
        kotlin.runCatching {
            getPersonDetailsUseCase.execute(personId, language)
        }
            .onSuccess {
                _person.value = it
            }
            .onFailure {
                _error.value = it
            }
    }

    private fun getDetailsState() {
        _detailsState = if (language == LanguageRepositoryImpl.RU) {
            PersonDetailsState.getRuDetails()
        } else {
            PersonDetailsState.getUsDetails()
        }
    }

    fun saveMovie(movie: MovieModel) = viewModelScope.launch {
        saveMovieUseCase.execute(movie)
    }
}