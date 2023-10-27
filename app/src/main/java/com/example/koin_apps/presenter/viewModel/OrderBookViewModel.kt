package com.example.koin_apps.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.api.orderBook.OrderBookEntity
import com.example.domain.entity.api.ticker.TickerOrderDataEntity
import com.example.domain.entity.api.transaction.TransactionEntity
import com.example.domain.usecase.apiUseCase.GetOrderBookUseCase
import com.example.domain.usecase.apiUseCase.GetTickerDetailInfoUseCase
import com.example.domain.usecase.apiUseCase.GetTransactionHistoryUseCase
import com.example.koin_apps.common.Constants
import com.example.koin_apps.di.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    private val getOrderBookUseCase: GetOrderBookUseCase,
    private val getTickerDetailInfoUseCase: GetTickerDetailInfoUseCase,
    private val getTransactionHistoryUseCase: GetTransactionHistoryUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tickerData = MutableLiveData<TickerOrderDataEntity>()
    private val _transactionData = MutableLiveData<List<TransactionEntity>>()
    private val _orderBookData = MutableLiveData<OrderBookEntity>()
    val tickerData: LiveData<TickerOrderDataEntity> get() = _tickerData
    val transactionData: LiveData<List<TransactionEntity>> get() = _transactionData
    val orderBookData: LiveData<OrderBookEntity> get() = _orderBookData

    fun fetchTickerData(ticker: String): Job = viewModelScope.launch(ioDispatcher) {
        while (true) {
            launch {
                getTickerDetailInfoUseCase(ticker).collect { _tickerData.postValue(it) }
                delay(Constants.DELAY_TIME_MILLIS)
            }.join()

            launch {
                getTransactionHistoryUseCase(ticker, 10).collect {
                    _transactionData.postValue(it)
                }
                delay(Constants.DELAY_TIME_MILLIS)
            }.join()

            launch {
                getOrderBookUseCase(ticker, 10).collect { _orderBookData.postValue(it) }
                delay(Constants.DELAY_TIME_MILLIS)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}