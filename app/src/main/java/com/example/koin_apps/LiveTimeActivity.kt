package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.common.Common
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.transaction.TransactionRoot
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.LiveTimeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        val koinName = intent.getStringExtra("KoinName")
        liveTimeBinding.selectedPrice.text = koinName

        koinTransactionCall(koinName)
        liveTimeBinding.getBackBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun loadKoinTransaction(koinName: String?): String {
        val koinTickerUrl = StringBuilder("https://api.bithumb.com/public/transaction_history/")
        koinTickerUrl.append(koinName)
        koinTickerUrl.append("_")
        koinTickerUrl.append("KRW")
        return koinTickerUrl.toString()
    }

    private fun koinTransactionCall(koinName: String?){

        koinService.getKoinTransaction(loadKoinTransaction(koinName))
            .enqueue(object: Callback<TransactionRoot>{
                override fun onResponse(
                    call: Call<TransactionRoot>,
                    response: Response<TransactionRoot>
                ) {
                    println("OK")
                    mKoinTransaction = response.body()
                    println(mKoinTransaction?.data)
                    liveTimeBinding.curPrice.text = mKoinTransaction?.data?.price.toString()

                }

                override fun onFailure(call: Call<TransactionRoot>, t: Throwable) { }

            })
    }

}