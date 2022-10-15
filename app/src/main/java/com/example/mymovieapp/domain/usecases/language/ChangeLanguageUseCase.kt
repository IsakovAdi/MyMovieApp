package com.example.mymovieapp.domain.usecases.language

import com.example.mymovieapp.domain.repositories.LanguageRepository

class ChangeLanguageUseCase (private val repository: LanguageRepository){
    fun execute():String = repository.saveLanguage()
}