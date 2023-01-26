package com.example.koin_apps.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.remote.model.transaction.TransactionData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TransactionViewModel(private val repos: AppRepository): ViewModel() {
    private val _transactionLiveData = MutableLiveData<ArrayList<TransactionData>>()
    val transactionLiveData: LiveData<ArrayList<TransactionData>>
        get() = _transactionLiveData

    private var vPath: String
    private var vCount: Int

    init {
        vPath = ""
        vCount = 5
    }

    private val transactionResponse = viewModelScope.launch {
        while (true) {
            val response = repos.getTransaction(vPath, vCount)
            if(response.isSuccessful) { _transactionLiveData.postValue(response.body()?.data) }
            delay(3000L)
        }
    }

    fun setOrderParameter(path: String) {
        vPath = path
        runTransactionScope()
    }

    private fun runTransactionScope() {
        if(vPath.isEmpty()) { throw (NullPointerException("Parameter is Empty")) }
        else { transactionResponse.start() }
    }

    override fun onCleared() {
        super.onCleared()
        transactionResponse.cancel()
    }
}