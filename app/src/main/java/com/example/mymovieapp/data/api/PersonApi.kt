package com.example.mymovieapp.data.api

import com.example.mymovieapp.data.network.model.response.person.PersonDetails
import com.example.mymovieapp.data.network.model.response.person.Persons
import com.example.mymovieapp.data.network.Endpoints.Person.PERSON_DETAILS
import com.example.mymovieapp.data.network.Endpoints.Person.PERSON_POPULAR
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonApi {

    @GET(PERSON_POPULAR)
    suspend fun getPersons(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Persons

    @GET(PERSON_DETAILS)
    suspend fun getPersonDetails(
        @Path("person_id") id:Int,
        @Query("api_key")api_key:String,
        @Query("language") language: String
    ): PersonDetails
}