package com.example.koin_apps.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TickerLiveDataEntity
import com.example.domain.usecase.apiUseCase.GetTickerLiveInfoUseCase
import com.example.koin_apps.common.Constants
import com.example.koin_apps.module.coroutineDispatcher.IoDispatcher
import kotlinx.coroutines.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveTimeViewModel @Inject constructor(
    private val getTickerInfoUseCase: GetTickerLiveInfoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tickerLiveViewData = MutableLiveData<TickerLiveDataEntity>()
    val tickerLiveViewData: LiveData<TickerLiveDataEntity> get() = _tickerLiveViewData

    fun loadTickerInfo(
        ticker: String
    ): Job = viewModelScope.launch(ioDispatcher) {
        while (true) {
            getTickerInfoUseCase(ticker).collect { _tickerLiveViewData.postValue(it) }
            delay(Constants.DELAY_TIME_MILLIS)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}