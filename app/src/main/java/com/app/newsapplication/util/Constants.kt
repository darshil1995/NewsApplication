package com.app.newsapplication.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

//Constants for our Applcation
class Constants {

    companion object {
        const val BASE_URL = "https://www.cbc.ca"
        const val PAGE_SIZE = 1
        const val TOTAL_RESULTS = 4
        val cacheSize = (5 * 1024 * 1024).toLong() //5MB
        const val CACHE_FILE_NAME = "news/a1.txt"
        const val CACHE_CONTROL_NAME = "Cache-Control"
        const val OFFLINE_CACHE_DATA_VALUE =
            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7   //7 days Old data
        const val ONLINE_CACHE_DATA_VALUE = "public, max-age=" + 5   //5 Seconds Old data


        //Check For Internet Connection
        fun hasInternetConnection(context: Context?): Boolean {
            val connectivityManager = context?.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val activeNetwork = connectivityManager.activeNetwork ?: return false
                val capabilities =
                    connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
                return when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            } else {
                connectivityManager.activeNetworkInfo?.run {
                    return when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
            return false
        }
    }
}