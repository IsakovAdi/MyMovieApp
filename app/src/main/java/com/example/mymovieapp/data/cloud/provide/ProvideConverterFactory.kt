package com.example.mymovieapp.data.cloud.provide

import retrofit2.Converter

interface ProvideConverterFactory {
    fun converterFactory():Converter.Factory
}