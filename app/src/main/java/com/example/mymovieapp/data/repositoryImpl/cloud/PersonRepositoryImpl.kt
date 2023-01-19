package com.example.mymovieapp.data.repositoryImpl.cloud

import com.example.mymovieapp.data.cloud.source.person.PersonCloudDataSource
import com.example.mymovieapp.data.models.person.PersonDetailsData
import com.example.mymovieapp.data.models.person.PersonsResponseData
import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.domain.models.person.PersonsResponseDomain
import com.example.mymovieapp.domain.repositories.network.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl (
    private val personCloudDataSource: PersonCloudDataSource,
    private val mapPersonResponse: Mapper<PersonsResponseData, PersonsResponseDomain>,
    private val mapPersonDetails: Mapper<PersonDetailsData, PersonDetailsDomain>,
    private val dispatchersProvider: DispatchersProvider,
) : PersonRepository {
    override fun getPersons(page: Int): Flow<PersonsResponseDomain> =
        personCloudDataSource.getPersons(page = page)
            .map(mapPersonResponse::map)
            .flowOn(dispatchersProvider.default())

    override suspend fun getPersonDetails(personId: Int): DataRequestState<PersonDetailsDomain> =
        personCloudDataSource.getPersonDetails(personId = personId)
            .map(mapPersonDetails)

}