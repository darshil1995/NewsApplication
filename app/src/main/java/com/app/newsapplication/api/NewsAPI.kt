package com.app.newsapplication.api

import com.app.newsapplication.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Api Interface class
interface NewsAPI {

    @GET("aggregate_api/v1/items")
    suspend fun getNews(
        @Query("lineupSlug")
        lineupSlug: String? = "news"
    ): Response<NewsResponse>
}