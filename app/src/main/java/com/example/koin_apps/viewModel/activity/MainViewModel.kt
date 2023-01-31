package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.*
import com.example.koin_apps.data.di.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repos: AppRepository
): ViewModel() { val readAllCoinData: LiveData<List<CoinEntity>> = repos.allCoinData }