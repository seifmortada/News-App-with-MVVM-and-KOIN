package com.example.newsapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.Article
import com.example.newsapp.models.ArticleData
import com.example.newsapp.models.Source
import com.example.newsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepo : NewsRepository)
    : ViewModel() {

    var articles: LiveData<Article> = MutableLiveData()
    var sources: LiveData<Source> = MutableLiveData()
    var savedArticles: LiveData<List<ArticleData>> = MutableLiveData()

    init {
        getArticles()
        getSources()

        articles = newsRepo.articles
        sources = newsRepo.sources
        savedArticles = newsRepo.savedArticles
    }

    private fun getArticles() =
        viewModelScope.launch {
            newsRepo.getArticles()
        }

    private fun getSources() =
        viewModelScope.launch {
            newsRepo.getSources()
        }

    fun saveArticle(article: ArticleData) = viewModelScope.launch {
        newsRepo.saveArticle(article)
    }
}

