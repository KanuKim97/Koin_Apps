package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.remote.model.ticker.TickerData
import kotlinx.coroutines.*


class LiveTimeViewModel(private val repos: AppRepository): ViewModel() {
    private val _tickerLiveViewData = MutableLiveData<List<TickerData>>()

    val tickerLiveViewData: LiveData<List<TickerData>>
        get() = _tickerLiveViewData

    fun getTickerLive(path: String) {
        val tickerResponse = viewModelScope.launch {
            while (true) {
                delay(3000L)
                Log.d("Coroutine","TickerResponse is still running")
            }
        }

        tickerResponse.start()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}