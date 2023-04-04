package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.*
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.di.repository.CoinTitleDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coinDBRepo: CoinTitleDBRepository
): ViewModel() {
    val readAllTicker: LiveData<List<CoinEntity>> get() = fetchDBData()

    private fun fetchDBData(): LiveData<List<CoinEntity>> = coinDBRepo.readAllCoinTitle
}