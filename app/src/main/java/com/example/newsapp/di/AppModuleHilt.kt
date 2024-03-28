//package com.example.newsapp.di
//
//import android.content.Context
//import androidx.room.Room
//import com.example.newsapp.api.RetrofitInterface
//import com.example.newsapp.database.NewsDao
//import com.example.newsapp.database.NewsDatabase
//import com.example.newsapp.database.UserDao
//import com.example.newsapp.database.UserDatabase
//import com.example.newsapp.repository.NewsRepository
//import com.example.newsapp.repository.UserRepository
//import com.example.newsapp.utils.Constants
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideNewsApi():RetrofitInterface {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(RetrofitInterface::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideNewsDatabase(@ApplicationContext context:Context):NewsDatabase {
//        return Room.databaseBuilder(context, NewsDatabase::class.java, "news_database").build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideNewsDao(newsDatabase:NewsDatabase):NewsDao {
//        return newsDatabase.userDao
//    }
//    @Provides
//    @Singleton
//    fun provideNewsRepository(api:RetrofitInterface,newsDao:NewsDao):NewsRepository{
//        return NewsRepository(api,newsDao)
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserDatabase(@ApplicationContext context:Context):UserDatabase {
//        return Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserDao(userDatabase: UserDatabase):UserDao {
//        return userDatabase.userDao
//    }
//    @Provides
//    @Singleton
//    fun provideUserRepository(userDao:UserDao):UserRepository{
//        return UserRepository(userDao)
//    }
//}