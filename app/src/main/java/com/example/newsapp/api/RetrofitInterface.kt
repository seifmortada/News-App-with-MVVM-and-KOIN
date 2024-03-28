package com.example.newsapp.api

import androidx.room.Query
import com.example.newsapp.models.Article
import com.example.newsapp.models.Source
import com.example.newsapp.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/v2/top-headlines/sources")
    suspend fun getSources(
       @retrofit2.http.Query("apiKey") apiKey :String = API_KEY
    ):Response<Source>

    // Iam adding the country in the url instead of a parameter of easy implementation
    @GET("/v2/top-headlines/")
    suspend fun getArticles(
        @retrofit2.http.Query("country") country :String = "us",
        @retrofit2.http.Query("apiKey") apiKey :String = API_KEY
    ):Response<Article>

}