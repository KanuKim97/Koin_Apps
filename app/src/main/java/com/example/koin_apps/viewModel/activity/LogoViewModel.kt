package com.example.koin_apps.viewModel.activity

import com.example.koin_apps.data.database.tables.CoinEntity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.di.repository.CoinTitleDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogoViewModel @Inject constructor(
    private val coinDBRepo: CoinTitleDBRepository
): ViewModel() {
    val readAllTicker: LiveData<List<CoinEntity>> get() = fetchDBData()

    private fun fetchDBData(): LiveData<List<CoinEntity>> = coinDBRepo.readAllCoinTitle
}