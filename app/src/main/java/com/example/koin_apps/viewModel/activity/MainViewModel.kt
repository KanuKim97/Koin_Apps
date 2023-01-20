package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.*
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity

class MainViewModel(private val repos: AppRepository): ViewModel() {
    var readAllCoinData: LiveData<List<CoinEntity>> = repos.allCoinData
}