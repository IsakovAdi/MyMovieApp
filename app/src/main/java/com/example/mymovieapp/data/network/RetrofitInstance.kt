package com.example.mymovieapp.data.network

import com.example.mymovieapp.data.api.MovieApi
import com.example.mymovieapp.data.api.PersonApi
import com.example.mymovieapp.data.api.VideoApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }

    val videoApi: VideoApi by lazy {
        retrofit.create(VideoApi::class.java)
    }

    val personApi: PersonApi by lazy {
        retrofit.create(PersonApi::class.java)
    }

}