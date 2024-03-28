package com.example.newsapp.di

import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.repository.UserRepository
import com.example.newsapp.viewmodel.NewsViewModel
import com.example.newsapp.viewmodel.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideApi() }
    single { provideNewsDatabase(androidContext()) }
    single { provideNewsDao(get()) }
    single { provideUserDatabase(androidContext()) }
    single { provideUserDao(get()) }

    factory { UserRepository(get()) }
    factory { NewsRepository(get(), get()) }

    viewModel() { RegisterViewModel(get()) }
    viewModel() { NewsViewModel(get()) }
}