package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TradeViewModel: ViewModel() {
    private val _tradeLiveData = MutableLiveData<Map<String, Any?>?>()

    val tradeLiveData: LiveData<Map<String, Any?>?>
        get() = _tradeLiveData


    init { _tradeLiveData.value = null }

    override fun onCleared() {
        super.onCleared()
        _tradeLiveData.value = null
    }

    fun updateKoinTrade(
        inputList: MutableMap<String, Any?>
    ) {
        _tradeLiveData.value = inputList
    }

}