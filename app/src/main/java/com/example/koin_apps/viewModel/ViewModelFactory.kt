package com.example.koin_apps.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository

class ViewModelFactory(private val repos: AppRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AppRepository::class.java).newInstance(repos)
    }
}