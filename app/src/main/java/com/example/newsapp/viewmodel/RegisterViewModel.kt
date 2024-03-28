package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.database.UserDatabase
import com.example.newsapp.models.User
import com.example.newsapp.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepo:UserRepository) : ViewModel() {
     var allUsers: LiveData<List<User>> = MutableLiveData()

    init {
        allUsers = userRepo.allUser
    }

    fun addUser(user: User) = viewModelScope.launch {
        userRepo.addUser(user)
    }
}