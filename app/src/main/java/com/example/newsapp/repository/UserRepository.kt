package com.example.newsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newsapp.database.UserDao
import com.example.newsapp.models.User
import com.example.newsapp.utils.Constants.TAG

class UserRepository(private val userDao: UserDao)
{
     val allUser: LiveData<List<User>> = userDao.getAllUsers()
    suspend fun addUser(user: User) {
        try {
            userDao.addUser(user)
        } catch (e: Exception) {
            Log.e(TAG, "addUser: ${e.message}")
        }
    }
}