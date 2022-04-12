package com.app.newsapplication.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.newsapplication.NewsApplication
import com.app.newsapplication.model.NewsResponse
import com.app.newsapplication.model.NewsResponseItem
import com.app.newsapplication.repository.NewsRepository
import com.app.newsapplication.util.Resource
import com.app.newsapplication.util.Utils
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(
    app: Application,
    val newsRepository: NewsRepository
) : AndroidViewModel(app) {

    val news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsTypeList: List<NewsResponseItem>? = null
    var newsPage = 1
    var newsResponse: NewsResponse? = null
    var category: String? = "news"

    init {
        getNews(category)
    }

    //Get All the news
    fun getNews(category: String?) = viewModelScope.launch {
        safeNewsCall(category)
    }

    //Handle News Response
    private fun handleNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                //increase page number
                newsPage++
                // Initialize the news response when null and
                // when it is not null we can just add te old and new newsponse items
                if (newsResponse == null) {
                    newsResponse = resultResponse
                } else {
                    val oldNews = newsResponse!!.toMutableList()
                    val newNews = resultResponse.toMutableList()

                    oldNews.addAll(newNews)
                }
                filterBy(newsResponse ?: resultResponse)
                var i = 0L
                (newsResponse ?: resultResponse).forEach {

                    val id = it.id
                    val active = it.active
                    val description = it.description
                    val embedTypes = it.embedTypes
                    val images = it.images
                    val language = it.language
                    val publishedAt = it.publishedAt
                    val readablePublishedAt = it.readablePublishedAt
                    val readableUpdatedAt = it.readableUpdatedAt
                    val source = it.source
                    val sourceId = it.sourceId
                    val title = it.title
                    val type = it.type
                    val typeAttributes = it.typeAttributes
                    val updatedAt = it.updatedAt
                    val version = it.version

                    val newsResponseItem = NewsResponseItem(
                        i,
                        id,
                        active,
                        description,
                        embedTypes,
                        images,
                        language,
                        publishedAt,
                        readablePublishedAt,
                        readableUpdatedAt,
                        source,
                        sourceId,
                        title,
                        type,
                        typeAttributes,
                        updatedAt,
                        version
                    )

                    saveNews(newsResponseItem)
                    i += 1
                }
                return Resource.Success(newsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    //For finding the  Type filter
    fun filterBy(newsResponse: NewsResponse): List<NewsResponseItem> {
        val outputList = newsResponse.toList().distinctBy { newsResponseItem ->
            newsResponseItem.type
        }
        newsTypeList = outputList
        return newsTypeList ?: outputList
    }

    //Save the News Item
    fun saveNews(newsResponseItem: NewsResponseItem) = viewModelScope.launch {
        newsRepository.upsert(newsResponseItem)
    }

    //Get all the news
    fun getAllSavedNews(): LiveData<List<NewsResponseItem>> {
        val list = newsRepository.getAllSavedNews()
        return list
    }

    //Delete the news Item
    fun deleteNews(newsResponseItem: NewsResponseItem) = viewModelScope.launch {
        newsRepository.deleteNews(newsResponseItem)
    }

    private suspend fun safeNewsCall(category: String?) {
        news.postValue(Resource.Loading())
        try {
            if (Utils().hasInternetConnection(getApplication<NewsApplication>())) {
                val response = newsRepository.getNews(category)
                news.postValue(handleNewsResponse(response))
            } else {
                news.postValue(Resource.Error("No Internet Connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> news.postValue(Resource.Error("Network Failure"))
                else -> news.postValue(Resource.Error("Error Occured"))
            }
        }
    }
}