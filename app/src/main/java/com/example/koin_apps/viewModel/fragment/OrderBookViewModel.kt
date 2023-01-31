package com.example.koin_apps.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.di.AppRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    private val repos: AppRepository
): ViewModel() {
    private val _orderBookLiveData = MutableLiveData<OrderData>()
    val orderBookLiveData: LiveData<OrderData>
        get() = _orderBookLiveData

    private val tickerJob: Job = viewModelScope.launch {
        while (true) {
            val response = repos.getTicker("Btc")
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.data.get("closing_price")
                response.body()!!.data.get("prev_closing_price")
                response.body()!!.data.get("max_price")
                response.body()!!.data.get("min_price")
                response.body()!!.data.get("units_traded_24H")
            }
            delay(1000L)
        }
    }

    private val transactionJob: Job = viewModelScope.launch {
        while (true) {
            val response = repos.getTransaction("BTC", 10)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.data
            }
        }
    }

    private val orderBookJob: Job = viewModelScope.launch {
        while (true) {
            val response = repos.getOrderBook("BTC", 10)
            if (response.isSuccessful && response.body() != null) {
                _orderBookLiveData.postValue(response.body()!!.data)
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        tickerJob.cancel()
        transactionJob.cancel()
        orderBookJob.cancel()
    }
}