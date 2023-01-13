package com.example.mymovieapp.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.cloud.base.ResourceProvider
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.person.PersonsResponseDomain
import com.example.mymovieapp.domain.repositories.network.PersonRepository
import com.example.mymovieapp.presentation.models.ResponseState
import com.example.mymovieapp.presentation.models.person.PersonsResponseUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PersonsViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val mapPersonResponseFromDomain: Mapper<PersonsResponseDomain, PersonsResponseUi>,
    private val resourceProvider: ResourceProvider,
    private val dispatchersProvider: DispatchersProvider,
) : ViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val personResponsePage = MutableStateFlow(1)

    private val _personResponseState = MutableStateFlow(ResponseState())
    val personResponseState get() = _personResponseState.asStateFlow()

    val persons = personResponsePage.flatMapLatest {
        personRepository.getPersons(personResponsePage.value)
    }.map(mapPersonResponseFromDomain::map)
        .flowOn(dispatchersProvider.default())
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .onEach {
            _personResponseState.emit(
                changeResponseState(
                    page = it.page,
                    totalPage = it.total_pages)
            )
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun nextPage() {
        personResponsePage.tryEmit(_personResponseState.value.nextPage)
    }

    fun previousPage() {
        personResponsePage.tryEmit(_personResponseState.value.previousPage)
    }


}