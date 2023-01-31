package com.example.koin_apps.viewModel.activity

import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.di.AppRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogoViewModel @Inject constructor(
    private val repos: AppRepository
): ViewModel() { val readAllData: LiveData<List<CoinEntity>> = repos.allCoinData }