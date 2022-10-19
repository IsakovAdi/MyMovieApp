package com.example.mymovieapp.domain.usecases.person

import com.example.mymovieapp.domain.models.person.PersonDetailsModel
import com.example.mymovieapp.domain.repositories.network.PersonRepository

class GetPersonDetailsUseCase(private val repository: PersonRepository) {
    suspend fun execute(personId: Int, language: String): PersonDetailsModel =
        repository.getPersonDetails(personId, language)
}