package com.example.mymovieapp.domain.repositories.network

import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.domain.models.person.PersonsResponseDomain
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPersons(page: Int): Flow<PersonsResponseDomain>
    suspend fun getPersonDetails(personId: Int): DataRequestState<PersonDetailsDomain>
}