package com.app.newsapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.app.newsapplication.model.NewsResponseItem

@Dao
interface NewsResponseItemDAO {

    @Insert(onConflict = REPLACE)
    suspend fun upsert(newsResponseItem: NewsResponseItem)

    @Query("SELECT * FROM newsresponseitems")
    fun getAllNewsResponseItems(): LiveData<List<NewsResponseItem>>

    @Delete()
    suspend fun deleteNewsResponseItem(newsResponseItem: NewsResponseItem)

    @Query("DELETE FROM newsresponseitems")
    suspend fun deleteAllNews()
}