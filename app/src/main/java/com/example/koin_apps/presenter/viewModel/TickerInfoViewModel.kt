package com.example.koin_apps.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.apiUseCase.GetOrderBookUseCase
import com.example.domain.usecase.apiUseCase.GetTickerDetailInfoUseCase
import com.example.domain.usecase.apiUseCase.GetTickerInfoUseCase
import com.example.domain.usecase.apiUseCase.GetTransactionHistoryUseCase
import com.example.koin_apps.di.qualifier.IoDispatcher
import kotlinx.coroutines.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TickerInfoViewModel @Inject constructor(
    private val getTickerInfoUseCase: GetTickerInfoUseCase,
    private val getOrderBookUseCase: GetOrderBookUseCase,
    private val getTickerDetailInfoUseCase: GetTickerDetailInfoUseCase,
    private val getTransactionHistoryUseCase: GetTransactionHistoryUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}