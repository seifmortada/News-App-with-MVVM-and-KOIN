package com.example.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.ArticleData
import com.example.newsapp.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<List<User>>

}