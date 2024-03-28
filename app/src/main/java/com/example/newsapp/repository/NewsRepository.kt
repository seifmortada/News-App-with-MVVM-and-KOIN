package com.example.newsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.api.RetrofitInterface
import com.example.newsapp.database.NewsDao
import com.example.newsapp.models.Article
import com.example.newsapp.models.ArticleData
import com.example.newsapp.models.Source
import com.example.newsapp.utils.Constants.TAG

class NewsRepository(private val api: RetrofitInterface,private val dao: NewsDao) {


    // Using livedata to observe the values that change

    private val _sources: MutableLiveData<Source> = MutableLiveData()
    val sources: LiveData<Source> = _sources

    private val _articles: MutableLiveData<Article> = MutableLiveData()
    val articles: LiveData<Article> = _articles

    val savedArticles: LiveData<List<ArticleData>> = dao.getAllArticles()

    // Making the network call to get the sources data
    suspend fun getSources() {
        try {
            val response = api.getSources()
            if (response.isSuccessful && response.body() != null) {
                response.body().let {
                    _sources.value = it
                }
            } else {
                Log.e(TAG, "Response is not successful or null ")
            }
        } catch (e: Exception) {
            Log.e(TAG, "getSources: ${e.message}")
        }
    }

    // Making the network call to get the articles data
    suspend fun getArticles() {
        try {
            val response = api.getArticles()
            if (response.isSuccessful && response.body() != null) {
                response.body().let {
                    _articles.value = it
                    Log.e(TAG, "Response is successful  ")
                }
            } else {
                Log.e(TAG, "Response is not successful or null ")
            }
        } catch (e: Exception) {
            Log.e(TAG, "getSources: ${e.message}")
        }
    }

    // Save The article
    suspend fun saveArticle(article: ArticleData) {
        try {
            dao.saveArticle(article)
        } catch (e: Exception) {
            Log.e(TAG, "saveArticle: ${e.message}")
        }
    }
}