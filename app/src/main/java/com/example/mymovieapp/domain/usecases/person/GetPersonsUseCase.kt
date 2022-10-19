package com.example.mymovieapp.domain.usecases.person

import com.example.mymovieapp.domain.models.person.PersonsModel
import com.example.mymovieapp.domain.repositories.network.PersonRepository

class GetPersonsUseCase(private val repository: PersonRepository) {
    suspend fun execute(language: String, page: Int): PersonsModel =
        repository.getPersons(language, page)
}