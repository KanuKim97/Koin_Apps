package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.di.repository.CoinRepository
import kotlinx.coroutines.*
import com.example.koin_apps.data.remote.model.ticker.LiveTickerData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveTimeViewModel @Inject constructor(
    private val coinApiRepos: CoinRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tickerLiveViewData = MutableLiveData<LiveTickerData>()
    val tickerLiveViewData: LiveData<LiveTickerData> get() = _tickerLiveViewData

    fun loadTickerInfo(tickerTitle: String) = viewModelScope.launch(ioDispatcher) {
        while (true) {
            coinApiRepos.getTickerInfoLive(tickerTitle).collect { result ->
                _tickerLiveViewData.postValue(result)
            }

            delay(Constants.DelayTimeMillis)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}