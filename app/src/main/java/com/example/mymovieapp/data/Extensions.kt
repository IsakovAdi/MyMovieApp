package com.example.mymovieapp.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.mymovieapp.presentation.App.Companion.instance

fun isMobileOnline(): Boolean {
    val connectivityManager =
        instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                return true
            }
        }
    }
    return false
}

fun timeFormat(minutes: Int?): String {
    return if (minutes != null) {
        val hours = (minutes / 60)
        val mins = (minutes % 60)
        String.format("%02d:%02d", hours, mins)
    } else "No value"
}