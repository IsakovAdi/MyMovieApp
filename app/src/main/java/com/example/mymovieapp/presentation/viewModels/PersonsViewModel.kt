package com.example.mymovieapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.domain.models.ResponseState
import com.example.mymovieapp.domain.models.person.PersonsModel
import com.example.mymovieapp.domain.usecases.language.GetLanguageUseCase
import com.example.mymovieapp.domain.usecases.person.GetPersonsUseCase
import kotlinx.coroutines.launch

class PersonsViewModel(
    private val getPersonsUseCase: GetPersonsUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
) : ViewModel() {

    private val _persons: MutableLiveData<PersonsModel> = MutableLiveData()
    val persons: LiveData<PersonsModel> get() = _persons

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    private var _responseState: MutableLiveData<ResponseState> = MutableLiveData()
    val responseState: LiveData<ResponseState> get() = _responseState

    private var _language: String = ""

    private var responsePage = 1

    init {
        _language = getLanguageUseCase.execute()
    }

    fun getPersons() = viewModelScope.launch {
        kotlin.runCatching {
            getPersonsUseCase.execute(_language, responsePage)
        }
            .onSuccess {
                _persons.value = it
                _responseState.value?.totalPage = it.total_pages
                initResponseState()
            }
            .onFailure {
                _error.value = it
            }
    }

    private fun initResponseState() {
        _responseState.value = ResponseState(
            page = _persons.value!!.page,
            previousPage = _persons.value!!.page - 1,
            nextPage = returnPage(),
            totalPage = _persons.value!!.total_pages,
            isHasPreviousPage = (_persons.value!!.page - 1) != 0,
            isHasNextPage = (_persons.value!!.page) != _persons.value!!.total_pages
        )
        responsePage = _responseState.value!!.page
    }

    private fun returnPage(): Int {
        return if (_persons.value!!.page == _persons.value!!.total_pages) {
            _persons.value!!.page
        } else {
            _persons.value!!.page + 1
        }
    }

    fun nextPage() {
        if (_persons.value!!.page != _persons.value!!.total_pages) {
            responsePage++
        }
        getPersons()
    }

    fun previousPage() {
        if (responsePage != 1) {
            responsePage--
        }
        getPersons()
    }
}