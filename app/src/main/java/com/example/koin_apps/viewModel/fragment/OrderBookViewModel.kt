package com.example.koin_apps.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.di.repository.ApiRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    private val bithumbApiRepos: ApiRepository
): ViewModel() {
    private val _transactionLiveData = MutableLiveData<ArrayList<TransactionData>>()
    private val _orderBookLiveData = MutableLiveData<OrderData>()
    private val _tickerLiveData = MutableLiveData<OrderTickerData>()

    val transactionLiveData: LiveData<ArrayList<TransactionData>> get() = _transactionLiveData
    val orderBookLiveData: LiveData<OrderData> get() = _orderBookLiveData
    val tickerLiveData: LiveData<OrderTickerData> get() = _tickerLiveData

    fun loadTickerInfo(tickerTitle: String) = viewModelScope.launch {
        while (true) {
            launch {
                val tickerResponse = bithumbApiRepos.getTicker(tickerTitle)
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

                delay(Constants.DelayTimeMillis)
            }.join()

            launch {
                val transactionResponse = bithumbApiRepos.getTransactionHistory(tickerTitle, 10)
                if (transactionResponse.isSuccessful && transactionResponse.body() != null) {
                    _transactionLiveData.postValue(transactionResponse.body()!!.data)
                }

                delay(Constants.DelayTimeMillis)
            }.join()

            launch {
                val orderBookResponse = bithumbApiRepos.getOrderBook(tickerTitle, 10)
                if (orderBookResponse.isSuccessful && orderBookResponse.body()!!.data != null) {
                    _orderBookLiveData.postValue(orderBookResponse.body()!!.data)
                }

                delay(Constants.DelayTimeMillis)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}