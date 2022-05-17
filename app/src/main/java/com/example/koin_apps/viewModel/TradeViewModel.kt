package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.transaction.TransactionList
import kotlinx.coroutines.DisposableHandle

class TradeViewModel: ViewModel() {
    private val _tradeLiveData = MutableLiveData<List<TransactionList>?>()
    private val _koinTradeArray = arrayListOf<TransactionList>()

    val tradeLiveData: LiveData<List<TransactionList>?>
        get() = _tradeLiveData

    var disposable: DisposableHandle? = null

    init {
        _tradeLiveData.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _tradeLiveData.value = null
    }

    fun updateKoinTrade(
        inputList: TransactionList
    ){
        _koinTradeArray.add(inputList)
        _tradeLiveData.value = _koinTradeArray
    }

    fun getApi(search_str: String){ }

}