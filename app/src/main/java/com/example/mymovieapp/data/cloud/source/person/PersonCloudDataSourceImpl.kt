package com.example.mymovieapp.data.cloud.source.person

import com.example.mymovieapp.data.cloud.api.PersonApi
import com.example.mymovieapp.data.cloud.model.person.PersonDetailsCloud
import com.example.mymovieapp.data.cloud.model.person.PersonsResponseCloud
import com.example.mymovieapp.data.cloud.source.ResponseHandler
import com.example.mymovieapp.data.models.person.PersonDetailsData
import com.example.mymovieapp.data.models.person.PersonsResponseData
import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PersonCloudDataSourceImpl(
    private val api: PersonApi,
    private val personResponseMapper: Mapper<PersonsResponseCloud, PersonsResponseData>,
    private val personDetailsMapper: Mapper<PersonDetailsCloud, PersonDetailsData>,
    private val responseHandler: ResponseHandler,
    private val dispatchersProvider: DispatchersProvider,
) : PersonCloudDataSource {
    override fun getPersons(
        page: Int,
    ): Flow<PersonsResponseData> = flow {
        emit(api.getPersons(page = page))
    }.flowOn(dispatchersProvider.io())
        .map { it.body() ?: PersonsResponseCloud.unknown() }
        .map(personResponseMapper::map)
        .flowOn(dispatchersProvider.default())

    override suspend fun getPersonDetails(
        personId: Int,
    ): DataRequestState<PersonDetailsData> =
        responseHandler.safeApiMapperCall(personDetailsMapper) {
            api.getPersonDetails(id = personId)
        }

}