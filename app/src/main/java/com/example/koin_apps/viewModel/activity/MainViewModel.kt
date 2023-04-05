package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.*
import com.example.koin_apps.data.database.tables.TickerEntity
import com.example.koin_apps.data.di.repository.TickerDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tickerDBRepo: TickerDBRepository
): ViewModel() {
    val readAllTicker: LiveData<List<TickerEntity>> get() = fetchDBData()

    private fun fetchDBData(): LiveData<List<TickerEntity>> = tickerDBRepo.readAllTicker
}