package com.example.mymovieapp.data.cloud.source.person

import com.example.mymovieapp.data.models.person.PersonDetailsData
import com.example.mymovieapp.data.models.person.PersonsResponseData
import com.example.mymovieapp.domain.DataRequestState
import kotlinx.coroutines.flow.Flow

interface PersonCloudDataSource {
    fun getPersons(page: Int): Flow<PersonsResponseData>
    suspend fun getPersonDetails(personId: Int): DataRequestState<PersonDetailsData>
}