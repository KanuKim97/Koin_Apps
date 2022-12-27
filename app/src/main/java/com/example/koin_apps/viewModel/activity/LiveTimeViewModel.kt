package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.model.transaction.TransactionList
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LiveTimeViewModel(private val repos: AppRepository): ViewModel() {
    private val _transactionLiveData = MutableLiveData<List<TransactionList>>()

    val transactionLiveData: LiveData<List<TransactionList>>
        get() = _transactionLiveData

    fun getTickerTransaction(path: String, count: Int) {
        viewModelScope.launch (Dispatchers.IO) {
            val response = repos.getTransaction(path, count)

            when (response.code()) {
                200 -> {
                    try {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            //TODO: Implementation Transaction response Data
                        }
                    } catch (e: NullPointerException) {
                        throw NullPointerException("Response Data is Null or Empty")
                    }
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

}