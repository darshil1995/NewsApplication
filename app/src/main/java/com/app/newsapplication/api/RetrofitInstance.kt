package com.app.newsapplication.api

import android.content.Context
import com.app.newsapplication.NewsApplication
import com.app.newsapplication.util.Constants
import com.app.newsapplication.util.Constants.Companion.BASE_URL
import com.app.newsapplication.util.Constants.Companion.CACHE_CONTROL_NAME
import com.app.newsapplication.util.Constants.Companion.OFFLINE_CACHE_DATA_VALUE
import com.app.newsapplication.util.Constants.Companion.ONLINE_CACHE_DATA_VALUE
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class RetrofitInstance(context: Context) {

    companion object {
        //Lazy used so that we have only one retrofit instance
        private val retrofit by lazy {
            val log = HttpLoggingInterceptor()
            log.setLevel(HttpLoggingInterceptor.Level.BODY)

            val myCache = Cache(
                File(NewsApplication.context?.cacheDir, Constants.CACHE_FILE_NAME),
                Constants.cacheSize
            )
            val client = OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor { chain ->
                    var request = chain.request()
                    request = if (Constants.hasInternetConnection(NewsApplication.context)!!)
                        request.newBuilder()
                            .header(CACHE_CONTROL_NAME, ONLINE_CACHE_DATA_VALUE)
                            .build()
                    else
                        request.newBuilder().header(
                            CACHE_CONTROL_NAME,
                            OFFLINE_CACHE_DATA_VALUE
                        ).build()
                    chain.proceed(request)
                }
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