package com.example.mymovieapp.domain.repositories.storage

interface LanguageRepository {
    fun saveLanguage():String
    fun getLanguage():String
}