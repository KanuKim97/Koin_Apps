package com.example.koin_apps.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.transaction.TransactionList
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot

class LiveTimeViewModel: ViewModel() {
    private val _transactionLiveData = MutableLiveData<List<TransactionList>?>()
    private val _koinTransactionArray = arrayListOf<TransactionList>()

    val transactionLiveData
        get() = _transactionLiveData


    init { _transactionLiveData.value = null }

    override fun onCleared() {
        super.onCleared()
        _transactionLiveData.value = null
    }

    fun updateKoinTransaction(
        inputTransaction: TransactionRoot?,
        transactionCount: Int
    ){
        if(inputTransaction != null) {
            if(inputTransaction.status == "0000"){

                for(i: Int in 0 until transactionCount-1) {
                    _koinTransactionArray.add(
                        TransactionList(
                            status = inputTransaction.status,
                            errorMsg = inputTransaction.message.toString(),
                            transactionDate = inputTransaction.data[i].transaction_date,
                            transactionType = inputTransaction.data[i].type,
                            units_Transaction_Traded = inputTransaction.data[i].units_traded,
                            transaction_Price = inputTransaction.data[i].price,
                            transaction_Total = inputTransaction.data[i].total
                        )
                    )
                }

                _transactionLiveData.value = _koinTransactionArray
            }
        }

    }

    fun updateErrorTransaction(
        inputErrorCode: String,
        inputErrorMsg: String
    ){
        _koinTransactionArray.add(
            TransactionList(
                status = inputErrorCode,
                errorMsg = inputErrorMsg,
                transactionDate = null,
                transactionType = null,
                units_Transaction_Traded = null,
                transaction_Price = null,
                transaction_Total = null
            )
        )

        _transactionLiveData.value = _koinTransactionArray
    }

}