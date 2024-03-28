package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.ArticleData
import com.example.newsapp.models.User

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: ArticleData)

    @Query("SELECT * FROM article_table")
    fun getAllArticles(): LiveData<List<ArticleData>>

}