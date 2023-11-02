package com.example.koin_apps.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.api.orderBook.OrderBookEntity
import com.example.domain.entity.api.ticker.TickerLiveDataEntity
import com.example.domain.entity.api.ticker.TickerOrderDataEntity
import com.example.domain.entity.api.transaction.TransactionEntity
import com.example.domain.usecase.apiUseCase.GetOrderBookUseCase
import com.example.domain.usecase.apiUseCase.GetTickerDetailInfoUseCase
import com.example.domain.usecase.apiUseCase.GetTickerLiveInfoUseCase
import com.example.domain.usecase.apiUseCase.GetTransactionHistoryUseCase
import com.example.koin_apps.common.Constants
import com.example.koin_apps.di.qualifier.IoDispatcher
import kotlinx.coroutines.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

@HiltViewModel
class TickerInfoViewModel @Inject constructor(
    private val getTickerInfoUseCase: GetTickerLiveInfoUseCase,
    private val getOrderBookUseCase: GetOrderBookUseCase,
    private val getTickerDetailInfoUseCase: GetTickerDetailInfoUseCase,
    private val getTransactionHistoryUseCase: GetTransactionHistoryUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tickerData = MutableStateFlow<TickerLiveDataEntity?>(null)
    private val _tickerDetailData = MutableStateFlow<TickerOrderDataEntity?>(null)
    private val _transactionData = MutableStateFlow<List<TransactionEntity>?>(null)
    private val _orderData = MutableStateFlow<OrderBookEntity?>(null)

    val tickerData: Flow<TickerLiveDataEntity> get() = _tickerData.filterNotNull()
    val tickerDetailData: Flow<TickerOrderDataEntity> get() = _tickerDetailData.filterNotNull()
    val transactionData: Flow<List<TransactionEntity>> get() = _transactionData.filterNotNull()
    val orderData: Flow<OrderBookEntity> get() = _orderData.filterNotNull()

    fun fetchTickerDetailInfo(ticker: String): Job = viewModelScope.launch(ioDispatcher) {
        launch {
            getTickerInfoUseCase(ticker).collect { _tickerData.value = it }
            delay(Constants.DELAY_TIME_MILLIS)
        }.join()

        launch {
            getTickerDetailInfoUseCase(ticker).collect { _tickerDetailData.value = it }
            delay(Constants.DELAY_TIME_MILLIS)
        }.join()

        launch {
            getTransactionHistoryUseCase(ticker, 30).collect { _transactionData.value = it }
            delay(Constants.DELAY_TIME_MILLIS)
        }.join()

        launch {
            getOrderBookUseCase(ticker, 30).collect { _orderData.value = it }
            delay(Constants.DELAY_TIME_MILLIS)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}