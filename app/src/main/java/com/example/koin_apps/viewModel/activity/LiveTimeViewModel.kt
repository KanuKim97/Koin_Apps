package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.remote.model.transaction.TransactionList
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LiveTimeViewModel(private val repos: AppRepository): ViewModel() {
    private val _transactionLiveData = MutableLiveData<List<TransactionList>?>()
    private val _koinTransactionArray = arrayListOf<TransactionList>()

    val transactionLiveData
        get() = _transactionLiveData


    init { _transactionLiveData.value = null }

    fun getTransaction(path: String, count: Int) {
        repos.getTransaction(path, count).enqueue(object: Callback<TransactionRoot>{
            override fun onResponse(
                call: Call<TransactionRoot>,
                response: Response<TransactionRoot>
            ) {
                when(response.code()) {
                    200 -> {
                        try {
                            val coinTransDesc = response.body()

                            Log.d("Transaction", "$coinTransDesc")
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

            override fun onFailure(call: Call<TransactionRoot>, t: Throwable) { t.printStackTrace() }
        })
    }

    override fun onCleared() {
        super.onCleared()
        _transactionLiveData.value = null
    }

/*    fun updateKoinTransaction(
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
    }*/

}