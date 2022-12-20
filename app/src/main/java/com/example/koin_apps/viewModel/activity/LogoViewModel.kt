package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import kotlinx.coroutines.launch

class LogoViewModel(private val repos: AppRepository): ViewModel() {
    private lateinit var _readAllData: LiveData<List<CoinEntity>>

    val readAllData: LiveData<List<CoinEntity>>
        get() = _readAllData

    init { viewModelScope.launch { _readAllData = repos.readAllData() } }
}