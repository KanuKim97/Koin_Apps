package com.example.koin_apps.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.di.repository.ApiRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import com.example.koin_apps.data.remote.model.ticker.OrderTickerData
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    private val bithumbApiRepos: ApiRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _transactionLiveData = MutableLiveData<ArrayList<TransactionData>>()
    private val _orderBookLiveData = MutableLiveData<OrderData>()
    private val _tickerLiveData = MutableLiveData<OrderTickerData>()

    val transactionLiveData: LiveData<ArrayList<TransactionData>> get() = _transactionLiveData
    val orderBookLiveData: LiveData<OrderData> get() = _orderBookLiveData
    val tickerLiveData: LiveData<OrderTickerData> get() = _tickerLiveData

    fun loadTickerData(tickerTitle: String) = viewModelScope.launch(ioDispatcher) {
        while (true) {
            launch {
                bithumbApiRepos.getTickerInfo(tickerTitle).collect {
                    _tickerLiveData.postValue(
                        OrderTickerData(
                            it.data["closing_price"].toString(),
                            it.data["prev_closing_price"].toString(),
                            it.data["max_price"].toString(),
                            it.data["min_price"].toString(),
                            it.data["units_traded_24H"].toString()
                        )
                    )
                }
                delay(Constants.DelayTimeMillis)
            }.join()

            launch {
                bithumbApiRepos.getTransactionInfo(tickerTitle, 10).collect {
                    _transactionLiveData.postValue(it.data!!)
                }
                delay(Constants.DelayTimeMillis)
            }.join()

            launch {
                bithumbApiRepos.getOrderBookInfo(tickerTitle, 10).collect {
                    _orderBookLiveData.postValue(it.data!!)
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