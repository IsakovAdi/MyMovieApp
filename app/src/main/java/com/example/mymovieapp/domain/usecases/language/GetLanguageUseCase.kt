package com.example.mymovieapp.domain.usecases.language

import com.example.mymovieapp.domain.repositories.storage.LanguageRepository

class GetLanguageUseCase (private val repository: LanguageRepository){
    fun execute() = repository.getLanguage()
}