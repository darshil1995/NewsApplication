package com.app.newsapplication.repository

import com.app.newsapplication.api.RetrofitInstance
import com.app.newsapplication.db.NewsResponseItemDatabase
import com.app.newsapplication.model.NewsResponseItem

//Get the functions from the API and DAO
class NewsRepository(
    val database: NewsResponseItemDatabase
) {
    suspend fun getNews(category: String?) = RetrofitInstance.api.getNews(lineupSlug = category)

    suspend fun upsert(newsResponseItem: NewsResponseItem) =
        database.getNewsResponseItemDAO().upsert(newsResponseItem)

    fun getAllSavedNews() = database.getNewsResponseItemDAO().getAllNewsResponseItems()

    suspend fun deleteNews(newsResponseItem: NewsResponseItem) =
        database.getNewsResponseItemDAO().deleteNewsResponseItem(newsResponseItem)
}