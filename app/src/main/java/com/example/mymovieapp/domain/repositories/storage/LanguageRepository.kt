package com.example.mymovieapp.domain.repositories.storage

interface LanguageRepository {
    suspend fun saveLanguage():String
    suspend fun getLanguage():String
}