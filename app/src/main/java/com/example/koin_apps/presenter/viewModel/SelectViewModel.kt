package com.example.koin_apps.presenter.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TickerEntity
import com.example.domain.usecase.apiUseCase.GetTickerAllUseCase
import com.example.domain.usecase.databaseUseCase.InsertTickerUseCase
import com.example.koin_apps.common.Constants
import com.example.koin_apps.module.coroutineDispatcher.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectViewModel @Inject constructor(
    private val getTickerAllUseCase: GetTickerAllUseCase,
    private val insertTickerUseCase: InsertTickerUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tickerList = MutableLiveData<List<String?>?>()
    val tickerList: LiveData<List<String?>?> get() = _tickerList

    init { loadTickerTitle() }

    private fun loadTickerTitle(): Job = viewModelScope.launch(ioDispatcher) {
        getTickerAllUseCase().collect { _tickerList.postValue(it.toList()) }
    }

    fun storeTickerTitle(tickerList: List<String>): Job = viewModelScope.launch(ioDispatcher) {
        tickerList.forEach { insertTickerUseCase(TickerEntity(it)) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}