package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectViewModel(private val repos: AppRepository): ViewModel() {
    private val _coinList = MutableLiveData<List<String?>?>()
    private val _selectedCoin = MutableLiveData<MutableList<String>>()

    val coinList: LiveData<List<String?>?>
        get() = _coinList
    val selectedCoin: LiveData<MutableList<String>>
        get()= _selectedCoin

    private var coinListElement: MutableList<String> = mutableListOf()

    init {
        _coinList.value = null
    }

    fun getTicker() {
        repos.getTicker("ALL").enqueue(object: Callback<TickerRoot> {
            override fun onResponse(
                call: Call<TickerRoot>,
                response: Response<TickerRoot>
            ) {
                when(response.code()) {
                    200 -> {
                        val coinTitleList = response.body()?.data?.keys?.toList()
                        _coinList.value = coinTitleList
                    }

                    400 -> { }
                }
            }

            override fun onFailure(call: Call<TickerRoot>, t: Throwable) { t.printStackTrace() }
        })
    }

    fun getData(Elements: MutableList<String>) { _selectedCoin.value = Elements }

    fun storeTitleData(){
        coinListElement = _selectedCoin.value!!

        if(coinListElement.size == 0) {
            CoinEntity(0, "Data is Empty")
        } else {
            for(listElement in coinListElement) {
                viewModelScope.launch(Dispatchers.IO) {
                    repos.addUser(CoinEntity(0, listElement))
                }
            }
        }
    }
}






