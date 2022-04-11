package com.app.newsapplication.api

import com.app.newsapplication.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        //Lazy used so that we have only one retrofit instance
        private val retrofit by lazy {
            val log = HttpLoggingInterceptor()
            log.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient
                .Builder()
                .addInterceptor(log)
                .build()

            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}