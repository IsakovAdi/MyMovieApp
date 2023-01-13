package com.example.mymovieapp.data.cloud.provide

import okhttp3.OkHttpClient

interface ProvideOkHttpClientBuilder {
    fun httpClientBuilder(): OkHttpClient
}