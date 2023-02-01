package com.example.koin_apps.viewModel.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.di.AppRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    private val repos: AppRepository
): ViewModel() {
    private val _transactionLiveData = MutableLiveData<ArrayList<TransactionData>>()
    private val _orderBookLiveData = MutableLiveData<OrderData>()
    private val _tickerLiveData = MutableLiveData<OrderTickerData>()

    val transactionLiveData: LiveData<ArrayList<TransactionData>>
        get() = _transactionLiveData
    val orderBookLiveData: LiveData<OrderData>
        get() = _orderBookLiveData
    val tickerLiveData: LiveData<OrderTickerData>
        get() = _tickerLiveData

    private var coinTitle: String = ""

    private val tickerJob: Job = viewModelScope.launch {
        while (true) {
            val response = repos.getTicker(coinTitle)
            if (response.isSuccessful && response.body() != null) {
                _tickerLiveData.postValue(
                    OrderTickerData(response.body()!!.data["closing_price"].toString(),
                        response.body()!!.data["prev_closing_price"].toString(),
                        response.body()!!.data["max_price"].toString(),
                        response.body()!!.data["min_price"].toString(),
                        response.body()!!.data["units_traded_24H"].toString()
                    )
                )
            }
            delay(1000L)
        }
    }

    private val transactionJob: Job = viewModelScope.launch {
        while (true) {
            val response = repos.getTransaction(coinTitle, 10)
            if (response.isSuccessful && response.body() != null) {
                _transactionLiveData.postValue(response.body()!!.data)
            }
            delay(1000L)
        }
    }

    private val orderBookJob: Job = viewModelScope.launch {
        while (true) {
            val response = repos.getOrderBook(coinTitle, 10)
            if (response.isSuccessful && response.body() != null) {
                _orderBookLiveData.postValue(response.body()!!.data)
            }
            delay(1000L)
        }
    }

    fun setCurrencyTitle(path: String) {
        coinTitle = path
        if (coinTitle.isNotEmpty()) { startAPICallJob() }
    }

    private fun startAPICallJob() {
        tickerJob.start()
        transactionJob.start()
        orderBookJob.start()
    }

    override fun onCleared() {
        super.onCleared()
        tickerJob.cancel()
        transactionJob.cancel()
        orderBookJob.cancel()
    }
}