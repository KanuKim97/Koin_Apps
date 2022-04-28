package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.transaction.TransactionList

class LiveTimeViewModel: ViewModel() {
    private val _transactionLiveData = MutableLiveData<List<TransactionList>?>()
    private val _koinTransactionArray = arrayListOf<TransactionList>()

    val transactionLiveData: LiveData<List<TransactionList>?>
        get() = _transactionLiveData

    init {
        _transactionLiveData.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _transactionLiveData.value = null
    }

    fun updateKoinTransaction(
        inputTransaction: TransactionList
    ){
        _koinTransactionArray.add(inputTransaction)
        _transactionLiveData.value = _koinTransactionArray
    }
}