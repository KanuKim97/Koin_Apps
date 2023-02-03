package com.example.koin_apps.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.di.AppRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
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

    private val apiCallJob: Job = viewModelScope.launch {
        while (true) {
            launch {
                val tickerResponse = repos.getTicker(coinTitle)
                if (tickerResponse.isSuccessful && tickerResponse.body() != null) {
                    _tickerLiveData.postValue(
                        OrderTickerData(
                            tickerResponse.body()!!.data["closing_price"].toString(),
                            tickerResponse.body()!!.data["prev_closing_price"].toString(),
                            tickerResponse.body()!!.data["max_price"].toString(),
                            tickerResponse.body()!!.data["min_price"].toString(),
                            tickerResponse.body()!!.data["units_traded_24H"].toString()
                        )
                    )
                }
                delay(1000)
            }.join()

            launch {
                val transactionResponse = repos.getTransaction(coinTitle, 10)
                if (transactionResponse.isSuccessful && transactionResponse.body() != null) {
                    _transactionLiveData.postValue(transactionResponse.body()!!.data)
                }
                delay(1000)
            }.join()

            launch {
                val orderBookResponse = repos.getOrderBook(coinTitle, 10)
                if (orderBookResponse.isSuccessful && orderBookResponse.body()!!.data != null) {
                    _orderBookLiveData.postValue(orderBookResponse.body()!!.data)
                }
                delay(1000)
            }
        }
    }

    fun setCurrencyTitle(path: String) {
        coinTitle = path
        if (coinTitle.isNotEmpty()) { startAPICallJob() }
    }

    private fun startAPICallJob() = apiCallJob.start()

    override fun onCleared() {
        super.onCleared()
        apiCallJob.cancel()
    }
}