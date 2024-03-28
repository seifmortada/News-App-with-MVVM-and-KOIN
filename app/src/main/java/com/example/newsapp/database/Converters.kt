package com.example.newsapp.database

import androidx.room.TypeConverter
import com.example.newsapp.models.ArticleSource
import com.example.newsapp.models.Source

class Converters {
    @TypeConverter
    fun fromSource(source: ArticleSource): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): ArticleSource {
        return ArticleSource("",name = name)
    }
}