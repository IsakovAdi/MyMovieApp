package com.example.mymovieapp.domain.repositories

interface LanguageRepository {
    fun saveLanguage():String
    fun getLanguage():String
}