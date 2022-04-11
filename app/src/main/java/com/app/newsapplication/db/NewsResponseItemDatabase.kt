package com.app.newsapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.newsapplication.model.NewsResponseItem

@Database(
    entities = [NewsResponseItem::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class NewsResponseItemDatabase : RoomDatabase() {
    abstract fun getNewsResponseItemDAO(): NewsResponseItemDAO

    companion object {
        @Volatile
        private var instance: NewsResponseItemDatabase?=null

        private val LOCK = Any()

        operator fun invoke(context:Context) = instance?: synchronized(LOCK){
            instance?:createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsResponseItemDatabase::class.java,
                "news_db.db"
            ).build()
    }
}