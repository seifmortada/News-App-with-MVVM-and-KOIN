package com.example.newsapp.models

data class Article(
    val articles: List<ArticleData>,
    val status: String,
    val totalResults: Int
)