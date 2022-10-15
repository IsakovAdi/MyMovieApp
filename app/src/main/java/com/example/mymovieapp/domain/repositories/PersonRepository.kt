package com.example.mymovieapp.domain.repositories

import com.example.mymovieapp.domain.models.person.PersonDetailsModel
import com.example.mymovieapp.domain.models.person.PersonsModel

interface PersonRepository {
    suspend fun getPersons(language:String, page:Int):PersonsModel
    suspend fun getPersonDetails(personId:Int, language:String) :PersonDetailsModel
}