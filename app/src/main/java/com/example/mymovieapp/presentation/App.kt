package com.example.mymovieapp.presentation

import android.app.Application
import com.example.mymovieapp.presentation.di.dataModule
import com.example.mymovieapp.presentation.di.domainModule
import com.example.mymovieapp.presentation.di.presentation
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
//            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(presentation, domainModule, dataModule))
        }
    }
    companion object {
        lateinit var instance: App
            private set
    }
}
