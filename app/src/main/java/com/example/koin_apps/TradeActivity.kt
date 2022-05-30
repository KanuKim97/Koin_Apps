package com.example.koin_apps

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.common.Common
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivityTradeBinding
import com.example.koin_apps.viewModel.TradeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class TradeActivity : AppCompatActivity() {
    private lateinit var tradeActivityBinding: ActivityTradeBinding
    private lateinit var tradeViewModel: TradeViewModel
    private lateinit var koinService: IKoinApiService

    private var threadNetwork: NetworkingThread? = null

    var koinTradeInfo: TickerRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tradeActivityBinding = ActivityTradeBinding.inflate(layoutInflater)
        tradeViewModel = ViewModelProvider(this)[TradeViewModel::class.java]
        koinService = Common.KoinApiService_public

        setContentView(tradeActivityBinding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        val tradeKoinName = intent.getStringExtra("KoinName")
        if (tradeKoinName != null) {
            setThread(tradeKoinName)
        }

        tradeViewModel.tradeLiveData.observe(this, {

            tradeActivityBinding.showTradedUnits.text =
                "코인 이름 : " + tradeKoinName +
                        "\nPrev_Closing_Price : "+ it?.get("Prev_Closing_Price").toString() +
                        "\nTrade_Value_24H : " + it?.get("TradeValue").toString() +
                        "\nFluctate_Rate_24H : " + it?.get("Fluctate_24H").toString()

        })
    }

//    override fun onRestart() {
//        super.onRestart()
//
//    }

    override fun onStop() {
        super.onStop()

        interruptThread()
    }

    override fun onDestroy() {
        super.onDestroy()

        interruptThread()
    }

    inner class NetworkingThread(
        tradeKoinName: String
    ) : Thread() {
        private val tradeCoin = tradeKoinName
        var isRunning = true

        override fun run() {

            while (isRunning){

                try {

                    koinTransactionCall(tradeCoin)
                    sleep(1000)

                } catch (e: InterruptedException) { e.printStackTrace() }

            }
        }

    }

    private fun setThread(tradeKoinName: String) {

        threadNetwork = NetworkingThread(tradeKoinName).apply { this.start() }

    }

    private fun interruptThread(){

        threadNetwork?.run {
            this.isRunning = false

            if (!this.isInterrupted)
                this.interrupt()
        }

    }



    private fun loadKoinTrade (koinName: String?): String {

        val koinTradeUrl = StringBuilder(Constants.IKoinApiUri)

        koinTradeUrl.append("ticker/")
        koinTradeUrl.append(koinName)
        koinTradeUrl.append("_")
        koinTradeUrl.append("KRW")

        return koinTradeUrl.toString()
    }

    private fun koinTransactionCall(koinName: String?){

        koinService.getKoinPrice(loadKoinTrade(koinName))
            .enqueue(object: Callback<TickerRoot> {
                override fun onResponse(
                    call: Call<TickerRoot>,
                    response: Response<TickerRoot>
                ) {

                    koinTradeInfo = response.body()

                    if(koinTradeInfo == null) {

                        Toast.makeText(
                            applicationContext,
                            "koin Information is Empty",
                            Toast.LENGTH_SHORT
                        ).show()

                        interruptThread()

                    } else {

                        val mKoinTradeInfo = mutableMapOf<String, Any?>()

                        mKoinTradeInfo["TradeValue"] = koinTradeInfo?.data?.acc_trade_value_24H
                        mKoinTradeInfo["Prev_Closing_Price"] = koinTradeInfo?.data?.prev_closing_price
                        mKoinTradeInfo["Fluctate_24H"] = koinTradeInfo?.data?.fluctate_rate_24H

                        tradeViewModel.updateKoinTrade(mKoinTradeInfo)

                    }

                }

                override fun onFailure(call: Call<TickerRoot>, t: Throwable) {

                    Toast.makeText(
                        applicationContext,
                        "${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            })
    }
}