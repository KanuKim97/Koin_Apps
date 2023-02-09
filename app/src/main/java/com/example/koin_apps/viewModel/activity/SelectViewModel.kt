package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.di.repository.ApiRepository
import com.example.koin_apps.data.di.repository.CoinTitleDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectViewModel @Inject constructor(
    private val ApiRepo: ApiRepository,
    private val coinDBRepo: CoinTitleDBRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _coinTitleList = MutableLiveData<List<String?>?>()
    private val tickerTitleJob: Job = viewModelScope.launch {
        val tickerTitleResponse = ApiRepo.getTickerAll()

        if (tickerTitleResponse.isSuccessful && tickerTitleResponse.body() != null) {
            val coinTitleList = tickerTitleResponse.body()?.data?.keys?.toList()
            _coinTitleList.postValue(coinTitleList)
        }
    }

    val coinTitleList: LiveData<List<String?>?>
        get() = _coinTitleList

    fun getCoinTitle() = tickerTitleJob.start()

    fun storeCoinTitle(selectedCoinTitle: List<String>) {
        viewModelScope.launch(ioDispatcher) {
            for (listElement in selectedCoinTitle) {
                coinDBRepo.insertCoinTitle(CoinEntity(listElement))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        tickerTitleJob.cancel()
        viewModelScope.cancel()
    }
}