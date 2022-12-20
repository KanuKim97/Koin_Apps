package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject

class SelectViewModel(private val repos: AppRepository): ViewModel() {
    private val _coinList = MutableLiveData<List<String?>?>()
    private val _selectedCoin = MutableLiveData<MutableList<String>>()
    private lateinit var _readAllData: LiveData<List<CoinEntity>>

    val readAllData: LiveData<List<CoinEntity>>
        get() = _readAllData
    val coinList: LiveData<List<String?>?>
        get() = _coinList
    val selectedCoin: LiveData<MutableList<String>>
        get()= _selectedCoin

    private var coinListElement: MutableList<String> = mutableListOf()

    init {
        _coinList.value = null
        viewModelScope.launch { _readAllData = repos.readAllData() }
    }

     fun getCoinTitle() {
         viewModelScope.launch(Dispatchers.IO) {
             val response = repos.getTicker("ALL")

             when (response.code()) {
                 200 -> {
                     val coinTitleList = response.body()?.data?.keys?.toList()
                     _coinList.postValue(coinTitleList)
                 }
                 400 -> {
                     val errJsonObj: JSONObject

                     try{
                         errJsonObj = JSONObject(response.errorBody()?.string()!!)
                         val responseErrCode = errJsonObj.getString("status")
                         val responseErrMsg = errJsonObj.getString("message")

                         Log.d("400 Error", "$responseErrCode: $responseErrMsg")
                     } catch (e: JSONException) { e.printStackTrace() }
                 }
             }

         }
    }

    fun getData(Elements: MutableList<String>) { _selectedCoin.value = Elements }

    fun storeTitleData(){
        try {
            coinListElement = _selectedCoin.value!!

            for(listElement in coinListElement) {
                viewModelScope.launch(Dispatchers.IO) {
                    repos.addCoinList(CoinEntity(0, listElement))
                }
            }
        } catch (e: NullPointerException) { e.printStackTrace() }
    }

}






