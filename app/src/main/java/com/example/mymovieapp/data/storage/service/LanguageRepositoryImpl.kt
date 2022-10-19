package com.example.mymovieapp.data.storage.service

import android.content.Context
import com.example.mymovieapp.domain.repositories.storage.LanguageRepository


class LanguageRepositoryImpl(context: Context) : LanguageRepository {
    companion object {
        private const val SHARED_PREFS_NAME = "language_prefs"
        private const val LANGUAGE_KEY = "language_prefs"
        const val RU = "ru"
        const val US = "US"
    }

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveLanguage(): String {
        val language = if (getLanguage() == RU) US
        else RU
        sharedPreferences.edit().putString(LANGUAGE_KEY, language).apply()
        return language
    }

    override fun getLanguage(): String {
        return sharedPreferences.getString(LANGUAGE_KEY, RU) ?: RU
    }

}