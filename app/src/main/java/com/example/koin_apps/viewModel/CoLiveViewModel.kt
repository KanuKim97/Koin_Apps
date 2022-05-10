package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.transaction.TransactionList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoLiveViewModel: ViewModel() {

    private val _koinData = MutableLiveData<List<TransactionList>?>()
    private val _koinArray = arrayListOf<TransactionList>()

    val koinData: LiveData<List<TransactionList>?>
        get() = _koinData

    fun comNetwork(param: String, inputTransaction: TransactionList)
    = viewModelScope.launch(Dispatchers.IO) {
        //val responseData = IKoinApiService.getIKoin(param)

        withContext(Dispatchers.Main){
            _koinArray.add(inputTransaction)
            _koinData.value = _koinArray
        }
    }

}