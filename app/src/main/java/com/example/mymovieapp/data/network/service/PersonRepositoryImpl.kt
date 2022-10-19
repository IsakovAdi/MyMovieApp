package com.example.mymovieapp.data.network.service

import com.example.mymovieapp.data.network.Resource
import com.example.mymovieapp.data.api.PersonApi
import com.example.mymovieapp.data.mappers.network.person.PersonDetailsMapper
import com.example.mymovieapp.data.mappers.network.person.PersonsMapper
import com.example.mymovieapp.data.network.Utils
import com.example.mymovieapp.domain.models.person.PersonDetailsModel
import com.example.mymovieapp.domain.models.person.PersonsModel
import com.example.mymovieapp.domain.repositories.network.PersonRepository

class PersonRepositoryImpl(
    private val api: PersonApi
) : PersonRepository, BaseRepository() {
    private val mapper = PersonsMapper()
    private val detailsMapper = PersonDetailsMapper()
    override suspend fun getPersons(language: String, page: Int): PersonsModel =
        safeApiCall {
            api.getPersons(Utils.API_KEY, language, page)
        }.let { response ->
            when (response) {
                is Resource.Success -> mapper.mapData(response.value)
                is Resource.Failure -> {
                    throw response.serviceException
                }
            }
        }

    override suspend fun getPersonDetails(personId: Int, language: String): PersonDetailsModel =
        safeApiCall {
            api.getPersonDetails(personId, Utils.API_KEY, language)
        }.let { response ->
            when (response) {
                is Resource.Success -> detailsMapper.mapData(response.value)
                is Resource.Failure -> {
                    throw  response.serviceException
                }
            }
        }
}