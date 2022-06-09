package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.common.Common
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.transaction.TransactionList
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.LiveTimeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NumberFormatException

class LiveTimeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding
    private lateinit var liveTimeViewModel: LiveTimeViewModel
    private lateinit var koinService: IKoinApiService

    private var transactionThread: TransactionThread? = null

    var mTransactionCoinData: TransactionRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)

        koinService = Common.KoinApiService_public
        liveTimeViewModel = ViewModelProvider(this)[LiveTimeViewModel::class.java]

        setContentView(liveTimeBinding.root)
    }


    override fun onResume() {
        super.onResume()

        val koinName = intent.getStringExtra("KoinName")
        val countTransaction: Int =
            try { liveTimeBinding.countTransaction.text.toString().toInt() }
            catch (e: NumberFormatException){ 0 }

        // setTransactionThread(koinName, countTransaction)

        liveTimeViewModel.transactionLiveData.observe(
            this,
            {
                val transactionResult = it

                if(transactionResult == null){
                    liveTimeBinding.TransactionView.text =
                        getString(R.string.transaction_Not_Founded)

                } else {

                    val transactionSize = (it.size)-1

                    for(i in 0 until transactionSize) {
                        liveTimeBinding.TransactionView.text =
                            getString(
                                R.string.transaction_Format,
                                it[i].transactionType,
                                it[i].transactionDate,
                                it[i].transaction_Price,
                                it[i].units_Transaction_Traded,
                                it[i].transaction_Total
                            )

                    }
                }

            })

        liveTimeBinding.BtnTransaction.isChecked = false
        liveTimeBinding.getBackBtn.setOnClickListener(this)

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

        when(v?.id) {
            R.id.getBackBtn ->
                startActivity(Intent(this, MainActivity::class.java))


        }

    }

    inner class TransactionThread(
        koinName: String?,
        countTransaction: Int
    ):Thread() {
        private val coinName: String =
            if (koinName.isNullOrEmpty()) {
                null.toString()
            } else { koinName }

        private val transactionNumber: Int = countTransaction
        var isRunning: Boolean = false

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
        if (!TransactionThread(koinName, countTransaction).isRunning){

            interruptThread()
        } else {

            transactionThread =
                TransactionThread(koinName, countTransaction)
                    .apply { this.start() }

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

                mTransactionCoinData = response.body()

                for (i: Int in 0 until transactionCount-1) {

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