package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.models.ArticleData
import com.example.newsapp.models.User

@Database(entities = [ArticleData::class], exportSchema = false, version = 1)
//@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val userDao: NewsDao
    companion object {
        @Volatile
        var INSTANCE: NewsDatabase? = null
        fun createDatabase(context: Context): NewsDatabase {
            var instance = INSTANCE
            if (instance != null)
                return instance
            else {
                instance =
                    Room.databaseBuilder(context, NewsDatabase::class.java, "news_database").build()
                return instance
            }
        }
    }
}