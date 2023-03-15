package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.di.repository.ApiRepository
import kotlinx.coroutines.*
import com.example.koin_apps.data.remote.model.ticker.LiveTickerData
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.ticker
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LiveTimeViewModel @Inject constructor(
    private val bithumbApiRepos: ApiRepository
): ViewModel() {
    private val _tickerLiveViewData = MutableLiveData<LiveTickerData>()
    val tickerLiveViewData: LiveData<LiveTickerData> get() = _tickerLiveViewData

    fun loadTickerPrice(tickerTitle: String) = viewModelScope.launch {
        while (true) {
            val tickerResponse: Response<TickerRoot> = bithumbApiRepos.getTicker(tickerTitle)

            if (tickerResponse.isSuccessful && tickerResponse.body() != null) {
                _tickerLiveViewData.postValue(
                    LiveTickerData(
                        tickerResponse.body()?.data?.get("closing_price").toString(),
                        tickerResponse.body()?.data?.get("fluctate_24H").toString(),
                        tickerResponse.body()?.data?.get("fluctate_rate_24H").toString()
                    )
                )
            }

            delay(Constants.DelayTimeMillis)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}