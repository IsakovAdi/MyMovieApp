package com.example.mymovieapp.domain.error

import android.content.Context
import android.widget.Toast

interface ErrorHandler {

    fun handle(throwable: Throwable)

    class Base(private val context: Context) : ErrorHandler {

        override fun handle(throwable: Throwable) {
            Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()
        }

    }
}