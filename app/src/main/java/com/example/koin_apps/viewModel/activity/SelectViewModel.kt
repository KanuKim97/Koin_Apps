package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.database.tables.TickerEntity
import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.di.repository.TickerRepository
import com.example.koin_apps.data.di.repository.TickerDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectViewModel @Inject constructor(
    private val tickerApiRepos: TickerRepository,
    private val tickerDBRepo: TickerDBRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tickerList = MutableLiveData<List<String?>?>()
    val tickerList: LiveData<List<String?>?> get() = _tickerList

    init { loadTickerTitle() }

    private fun loadTickerTitle(): Job = viewModelScope.launch(ioDispatcher) {
        tickerApiRepos.getTickerInfoALL().collect { _tickerList.postValue(it) }
    }

    fun storeTickerTitle(tickerList: List<String>): Job = viewModelScope.launch(ioDispatcher) {
        for (ticker in tickerList) { tickerDBRepo.insertTicker(TickerEntity(ticker)) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}