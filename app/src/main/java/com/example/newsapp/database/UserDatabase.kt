package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.models.ArticleData
import com.example.newsapp.models.User

@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    companion object {
        @Volatile
        var INSTANCE: UserDatabase? = null
        fun createDatabase(context: Context): UserDatabase {
            var instance = INSTANCE
            if (instance != null)
                return instance
            else {
                instance =
                    Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()
                return instance
            }
        }
    }
}