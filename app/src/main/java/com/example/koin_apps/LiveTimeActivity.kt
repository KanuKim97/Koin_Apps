package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.common.Common
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.transaction.TransactionList
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.LiveTimeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NumberFormatException
import java.lang.StringBuilder

class LiveTimeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding
    private lateinit var liveTimeViewModel: LiveTimeViewModel
    private lateinit var koinService: IKoinApiService

    var mKoinTransaction: TransactionRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)
        setContentView(liveTimeBinding.root)

        koinService = Common.KoinApiService_public
        liveTimeViewModel = ViewModelProvider(this)[LiveTimeViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()

        liveTimeBinding.getTransactionBtn.setOnClickListener(this)
        liveTimeBinding.getBackBtn.setOnClickListener(this)

        liveTimeViewModel.transactionLiveData.observe(
            this,
            {
                Log.d("Result", it.toString())
                liveTimeBinding.TransactionView.text = it.toString()
            }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        liveTimeBinding.countTransaction.text?.clear()
    }

    override fun onClick(v: View?) {

        val koinName = intent.getStringExtra("KoinName")
        val countTransaction: Int =
            try {
                liveTimeBinding.countTransaction.text.toString().toInt()
            } catch (e: NumberFormatException){
                0
            }


        when(v?.id) {
            R.id.getTransactionBtn ->
                if (
                    liveTimeBinding.countTransaction.text.isNullOrEmpty()
                    or
                    (countTransaction < 1)
                ){
                    Toast.makeText(this, "Input count Plz", Toast.LENGTH_SHORT).show()
                    liveTimeBinding.countTransaction.text?.clear()

                } else {
                    koinTransactionCall(koinName, countTransaction)
                }

            R.id.getBackBtn ->
                startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun loadKoinTransaction(koinName: String?, countTransaction: Int): String {

        val koinTransactionUrl = StringBuilder(Constants.IKoinApiUri)

        koinTransactionUrl.append("transaction_history/")
        koinTransactionUrl.append(koinName)
        koinTransactionUrl.append("_")
        koinTransactionUrl.append("KRW")
        koinTransactionUrl.append("?count=${countTransaction}")

        return koinTransactionUrl.toString()
    }

    private fun koinTransactionCall(koinName: String?, countTransaction: Int){

        koinService.getKoinTransaction(loadKoinTransaction(koinName, countTransaction))
            .enqueue(object: Callback<TransactionRoot>{
                override fun onResponse(
                    call: Call<TransactionRoot>,
                    response: Response<TransactionRoot>
                ) {
                    mKoinTransaction = response.body()
                    for (i: Int in 0 until countTransaction-1){

                        val transactionKoinList = TransactionList(
                            mKoinTransaction?.data?.get(i)?.transaction_date!!,
                            mKoinTransaction?.data?.get(i)?.type!!,
                            mKoinTransaction?.data?.get(i)?.units_traded!!,
                            mKoinTransaction?.data?.get(i)?.price!!,
                            mKoinTransaction?.data?.get(i)?.total!!
                        )

                        liveTimeViewModel.updateKoinTransaction(transactionKoinList)

                    }

                }

                override fun onFailure(call: Call<TransactionRoot>, t: Throwable) {
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

}