package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.transaction.TransactionList
import kotlinx.coroutines.DisposableHandle

class TradeViewModel: ViewModel() {
    private val _tradeLiveData = MutableLiveData<List<String>?>()

    val tradeLiveData: LiveData<List<String>?>
        get() = _tradeLiveData

    init {
        _tradeLiveData.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _tradeLiveData.value = null
    }

    fun updateKoinTrade(
        inputList: MutableList<String>
    ) {
        _tradeLiveData.value = inputList
    }

}