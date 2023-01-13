package com.example.mymovieapp.data.cloud.api

import com.example.mymovieapp.data.cloud.Endpoints.Person.PERSON_DETAILS
import com.example.mymovieapp.data.cloud.Endpoints.Person.PERSON_POPULAR
import com.example.mymovieapp.data.cloud.Utils
import com.example.mymovieapp.data.cloud.model.person.PersonDetailsCloud
import com.example.mymovieapp.data.cloud.model.person.PersonsResponseCloud
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonApi {

    @GET(PERSON_POPULAR)
    suspend fun getPersons(
        @Query("api_key") api_key: String = Utils.API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int,
    ): Response<PersonsResponseCloud>

    @GET(PERSON_DETAILS)
    suspend fun getPersonDetails(
        @Path("person_id") id: Int,
        @Query("api_key") api_key: String = Utils.API_KEY,
        @Query("language") language: String = "ru",
    ): Response<PersonDetailsCloud>
}