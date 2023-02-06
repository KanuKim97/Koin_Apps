package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import com.example.koin_apps.data.di.AppRepository
import com.example.koin_apps.data.remote.model.ticker.LiveTickerData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveTimeViewModel @Inject constructor(
    private val repos: AppRepository
): ViewModel() {
    private val _tickerLiveViewData = MutableLiveData<LiveTickerData>()
    val tickerLiveViewData: LiveData<LiveTickerData>
        get() = _tickerLiveViewData

    private var path: String = ""
    private val tickerLiveJob: Job = viewModelScope.launch {
        while (true) {
            val response = repos.getTicker(path = path)
            if (response.isSuccessful && response.code() == 200) {
                val resultData = LiveTickerData(
                    response.body()?.data?.get("closing_price").toString(),
                    response.body()?.data?.get("fluctate_24H").toString(),
                    response.body()?.data?.get("fluctate_rate_24H").toString()
                )

                _tickerLiveViewData.postValue(resultData)
            }
            delay(1000L)
        }
    }

    fun setTickerPath(coinTitle: String) {
        path = coinTitle
        getTickerLiveData()
    }

    private fun getTickerLiveData() { if (path.isNotEmpty()) { tickerLiveJob.start() } }
    override fun onCleared() {
        super.onCleared()
        tickerLiveJob.cancel()
    }

}