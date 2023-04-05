package com.example.koin_apps.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.di.repository.TickerRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    private val tickerApiRepos: TickerRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tickerData = MutableLiveData<OrderTickerData>()
    private val _transactionData = MutableLiveData<ArrayList<TransactionData>>()
    private val _orderBookData = MutableLiveData<OrderData>()
    val tickerData: LiveData<OrderTickerData> get() = _tickerData
    val transactionData: LiveData<ArrayList<TransactionData>> get() = _transactionData
    val orderBookData: LiveData<OrderData> get() = _orderBookData

    fun fetchTickerData(ticker: String): Job = viewModelScope.launch (ioDispatcher) {
        while (true) {
            launch {
                tickerApiRepos.getTickerInfoDetail(ticker).collect {
                    _tickerData.postValue(it)
                }
                delay(Constants.DelayTimeMillis)
            }.join()

            launch {
                tickerApiRepos.getTransactionInfo(ticker, 10).collect {
                    _transactionData.postValue(it)
                }
                delay(Constants.DelayTimeMillis)
            }.join()

            launch {
                tickerApiRepos.getOrderBookInfo(ticker, 10).collect {
                    _orderBookData.postValue(it)
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