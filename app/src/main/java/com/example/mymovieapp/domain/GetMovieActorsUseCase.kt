package com.example.mymovieapp.domain

import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.domain.repositories.network.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetMovieActorsUseCase {
    operator fun invoke(actorsIds: List<Int>): Flow<List<DataRequestState<PersonDetailsDomain>>>
}

class GetMovieActorsUseCaseImpl @Inject constructor(
    private val repository: PersonRepository,
    private val dispatchersProvider: DispatchersProvider,
) : GetMovieActorsUseCase {
    override fun invoke(actorsIds: List<Int>): Flow<List<DataRequestState<PersonDetailsDomain>>> =
        flow { emit(getActors(actorsIds)) }.flowOn(dispatchersProvider.io())

    suspend fun getActors(actorsId: List<Int>?): List<DataRequestState<PersonDetailsDomain>> {
        if (actorsId == null) return listOf(DataRequestState.Error(NullPointerException("parametrs is null")))
        return actorsId.map { id -> repository.getPersonDetails(id) }
    }
}