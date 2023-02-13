package com.example.koin_apps.viewModel.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.di.AppRepository
import com.example.koin_apps.data.remote.model.orderBook.OrderData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderBookViewModel @Inject constructor(
    private val repos: AppRepository
): ViewModel() {
    private val _orderBookLiveData = MutableLiveData<OrderData>()
    val orderBookLiveData: LiveData<OrderData>
        get() = _orderBookLiveData

    fun getOrderBookData(path: String, count: Int) = viewModelScope.launch {
        val response = repos.getOrderBook(path, count)
        when(response.code()) {
            200 -> {
                try {
                    if(response.isSuccessful)
                        _orderBookLiveData.postValue(response.body()?.data)
                } catch (e: NullPointerException) { throw (e) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}