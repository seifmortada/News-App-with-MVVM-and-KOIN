package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val name:String,
    val password:String
)
