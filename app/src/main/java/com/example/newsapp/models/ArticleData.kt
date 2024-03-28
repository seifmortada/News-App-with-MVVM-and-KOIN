package com.example.newsapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "article_table")
data class ArticleData(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
//    val source: ArticleSource,
    val title: String,
    val url: String,
    val urlToImage: String
)