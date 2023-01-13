package com.example.mymovieapp.data.cloud.provide

interface MakeService {
    fun <T> service(clasz: Class<T>): T
}