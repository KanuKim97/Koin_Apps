package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.data.remote.model.tickerTitle.CoinTitleData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectViewModel(private val repos: AppRepository): ViewModel() {

    private val _selectKoinList = MutableLiveData<List<String?>?>()

    val selectKoinList: LiveData<List<String?>?>
        get() = _selectKoinList

    val coinTitleData = MutableLiveData<CoinEntity>()

    init { _selectKoinList.value = null }

    fun getTicker() {
        repos.getTicker("ALL").enqueue(object: Callback<TickerRoot> {
            override fun onResponse(
                call: Call<TickerRoot>,
                response: Response<TickerRoot>
            ) {
                when(response.code()) {
                    200 -> {
                        Log.d("200 Value", "Coin Title: ${response.body()?.data?.keys}")
                        val coinTitleList = response.body()?.data?.keys?.toList()

                        _selectKoinList.value = coinTitleList
                    }

                    400 -> {
                        coinTitleData.postValue(
                            CoinEntity(
                                0,
                                "",
                                false
                            ).apply { uid = response.code() }
                        )
                    }
                }
            }

            override fun onFailure(call: Call<TickerRoot>, t: Throwable) { t.printStackTrace() }
        })
    }

}






