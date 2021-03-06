package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetrofitClient
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.requestError.RequestErrorRoot
import com.example.koin_apps.data.remote.model.transaction.TransactionList
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.LiveTimeViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NumberFormatException

class LiveTimeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding
    private lateinit var liveTimeViewModel: LiveTimeViewModel
    private lateinit var koinService: IKoinApiService

    private lateinit var koinName: String
    private var cntTransaction: Int = 0

    private var transactionThread: TransactionThread? = null
    private var toggleValue: Boolean = false

    var mTransactionCoinData: TransactionRoot? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)

        liveTimeViewModel = ViewModelProvider(this)[LiveTimeViewModel::class.java]
        koinService = RetrofitClient.koinApiService_Public
        koinName = intent.getStringExtra("KoinName").toString()

        setContentView(liveTimeBinding.root)
    }

    override fun onResume() {
        super.onResume()

        liveTimeViewModel.transactionLiveData.observe(
            this,
            { transactionResult ->

                if(transactionResult == null){
                    liveTimeBinding.TransactionView.text =
                        getString(R.string.transaction_Not_Founded)
                } else {

                    val transactionSize = (transactionResult.size)-1

                    for(i in 0 until transactionSize) {
                        liveTimeBinding.TransactionView.text =
                            getString(
                                R.string.transaction_Format,
                                transactionResult[i].transactionType,
                                transactionResult[i].transactionDate,
                                transactionResult[i].transaction_Price,
                                transactionResult[i].units_Transaction_Traded,
                                transactionResult[i].transaction_Total
                            )

                    }
                }

            })

        liveTimeBinding.getBackBtn.setOnClickListener(this)
        liveTimeBinding.BtnTransaction.setOnClickListener(this)
    }

    override fun onPause() {
        super.onPause()
        interruptThread()
    }

    override fun onStop() {
        super.onStop()
        interruptThread()
    }

    override fun onDestroy() {
        super.onDestroy()
        interruptThread()
        liveTimeBinding.countTransaction.text?.clear()
    }

    override fun onClick(v: View?) {

        cntTransaction =
            try { liveTimeBinding.countTransaction.text.toString().toInt() }
            catch (e:NumberFormatException) { 0 }

        when(v?.id) {

            R.id.getBackBtn ->
                startActivity(Intent(this, MainActivity::class.java))

            R.id.Btn_Transaction -> {

                if(toggleValue) {

                    toggleValue = false
                    interruptThread()

                } else {

                    toggleValue = true
                    setTransactionThread(koinName, cntTransaction)

                }

            }

        }

    }

    inner class TransactionThread(
        koinName: String?,
        countTransaction: Int
    ): Thread() {

        private val coinName: String =
            if (koinName.isNullOrEmpty()) { null.toString() }
            else { koinName }

        private val transactionNumber: Int = countTransaction
        var isRunning: Boolean = true

        override fun run() {

            while (isRunning) {

                try {
                    transactionCoinResponse(coinName, transactionNumber)
                    sleep(1000)
                } catch (e: InterruptedException) { e.printStackTrace() }

            }

        }

    }

    private fun setTransactionThread(koinName: String?, countTransaction: Int) {

        if (!TransactionThread(koinName, countTransaction).isRunning) {
            interruptThread()
        } else {
            transactionThread =
                TransactionThread(koinName, countTransaction).apply { this.start() }
        }

    }

    private fun interruptThread() {

        transactionThread?.run {
            this.isRunning = false

            if(!this.isInterrupted)
                this.interrupt()
        }

    }

    private fun transactionCoinResponse(coinName: String, transactionCount: Int) {

        val mTransactionCoin = RetrofitRepo.getTransactionSingleton(coinName, transactionCount)

        mTransactionCoin.enqueue(object: Callback<TransactionRoot>{
            override fun onResponse(
                call: Call<TransactionRoot>,
                response: Response<TransactionRoot>
            ) {

                when(response.code()) {

                    200 -> {

                        mTransactionCoinData = response.body()

                        for(i: Int in  0 until transactionCount-1) {

                            val transactionCoinList = TransactionList(
                                mTransactionCoinData?.data?.get(i)?.transaction_date!!,
                                mTransactionCoinData?.data?.get(i)?.type!!,
                                mTransactionCoinData?.data?.get(i)?.units_traded!!,
                                mTransactionCoinData?.data?.get(i)?.price!!,
                                mTransactionCoinData?.data?.get(i)?.total!!
                            )

                            liveTimeViewModel.updateKoinTransaction(transactionCoinList)
                        }

                    }

                    400 -> {
                        //TODO need to Error Handling
                        val jsonObject: JSONObject
                        val requestErrorBody: RequestErrorRoot

                        try {

                            jsonObject = JSONObject(response.errorBody()!!.string())

                            val responseCode = jsonObject.getString("status")
                            val responseMsg = jsonObject.getString("message")

                            requestErrorBody = RequestErrorRoot(responseCode, responseMsg)

                            println(requestErrorBody)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }

                }



            }

            override fun onFailure(call: Call<TransactionRoot>, t: Throwable) {

                Toast.makeText(
                    applicationContext,
                    "${t.message}",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })

    }

}