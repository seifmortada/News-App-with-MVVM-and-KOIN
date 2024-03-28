package com.example.newsapp.di

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Room
import com.example.newsapp.api.RetrofitInterface
import com.example.newsapp.database.NewsDao
import com.example.newsapp.database.NewsDatabase
import com.example.newsapp.database.UserDao
import com.example.newsapp.database.UserDatabase
import com.example.newsapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideApi():RetrofitInterface =
         Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface::class.java)

fun provideNewsDatabase(context:Context):NewsDatabase =
    Room.databaseBuilder(context, NewsDatabase::class.java, "news_database").build()

fun provideNewsDao(db: NewsDatabase) : NewsDao = db.userDao

fun provideUserDatabase(context:Context):UserDatabase =
    Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()

fun provideUserDao(db: UserDatabase) : UserDao = db.userDao