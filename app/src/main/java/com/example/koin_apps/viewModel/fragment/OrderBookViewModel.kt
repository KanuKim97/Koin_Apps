package com.example.koin_apps.viewModel.fragment

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.di.coroutineDispatcher.MainDispatcher
import com.example.koin_apps.data.di.repository.CoinRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    private val coinApiRepos: CoinRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _transactionLiveData = MutableLiveData<ArrayList<TransactionData>>()
    private val _orderBookLiveData = MutableLiveData<OrderData>()
    private val _tickerLiveData = MutableLiveData<OrderTickerData>()

    val transactionLiveData: LiveData<ArrayList<TransactionData>> get() = _transactionLiveData
    val orderBookLiveData: LiveData<OrderData> get() = _orderBookLiveData
    val tickerLiveData: LiveData<OrderTickerData> get() = _tickerLiveData

    fun loadTickerData(tickerTitle: String): Job = viewModelScope.launch(ioDispatcher) {
        while (true) {
            launch {
                coinApiRepos.getTickerInfoDetail(tickerTitle).collect { result ->
                    _tickerLiveData.postValue(result)
                }
                delay(Constants.DelayTimeMillis)
            }.join()

            launch {
                coinApiRepos.getTransactionInfo(tickerTitle, 10).collect { result ->
                    _transactionLiveData.postValue(result)
                }
                delay(Constants.DelayTimeMillis)
            }.join()

            launch {
                coinApiRepos.getOrderBookInfo(tickerTitle, 10).collect { result ->
                    _orderBookLiveData.postValue(result)
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