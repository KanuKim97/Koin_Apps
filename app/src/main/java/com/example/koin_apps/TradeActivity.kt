package com.example.koin_apps

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    private val tradeKoinName = "BTC"

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

        if (tradeKoinName != null) {
            setThread(tradeKoinName)
        }

        tradeViewModel.tradeLiveData.observe(this, {
            Log.d("Trade Live Data", it.toString())

            Log.d("Prev_Closing_Price", it?.get("Prev_Closing_Price").toString())
            Log.d("Trade Value", it?.get("TradeValue").toString())
            Log.d("Fluctate_24H",  it?.get("Fluctate_24H").toString())

            tradeActivityBinding.showTradedUnits.text =
                "코인 이름 : " + tradeKoinName +
                        "\n종전가 : "+ it?.get("Prev_Closing_Price").toString() +
                        "\n현재가 : " + it?.get("TradeValue").toString() +
                        "\n24시 변동률 : " + it?.get("Fluctate_24H").toString()

        })
    }

    override fun onRestart() {
        super.onRestart()

        if (tradeKoinName != null)
            setThread(tradeKoinName)
        else
            Toast.makeText(this, " ", Toast.LENGTH_SHORT).show()

        Log.d("onRestarted", "onRestarted: " + threadNetwork?.isRunning)
    }

    override fun onStop() {
        super.onStop()

        interruptThread()
        Log.d("Thread interrupted", "All Thread is interrupted")
    }

    override fun onDestroy() {
        super.onDestroy()

        interruptThread()
        Log.d("Thread interrupted", "All Thread is interrupted")

    }

    inner class NetworkingThread(
        tradeKoinName: String
    ) : Thread() {
        private val tradeCoin = tradeKoinName
        var isRunning = true

        override fun run() {

            while (isRunning){

                try {
                    Log.d("Network Thread", "is Running!")
                    koinTransactionCall(tradeCoin)

                    sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }

        }

    }

    private fun setThread(tradeKoinName: String) {

        threadNetwork = NetworkingThread(tradeKoinName).apply {
            this.start()
            Log.d("NetWorkThread", "Thread is started")
        }

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

        Log.d("API_ADDRESS: ", loadKoinTrade(koinName))

        koinService.getKoinPrice(loadKoinTrade(koinName))
            .enqueue(object: Callback<TickerRoot> {
                override fun onResponse(
                    call: Call<TickerRoot>,
                    response: Response<TickerRoot>
                ) {
                    koinTradeInfo = response.body()

                    Log.d("body()", "${response.body()}")

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

                        Log.d("Trade Info : Status: ", "${koinTradeInfo?.status}")
                        Log.d("Trade Info : Data", "$mKoinTradeInfo")


                        tradeViewModel.updateKoinTrade(mKoinTradeInfo)

                    }

                }

                override fun onFailure(call: Call<TickerRoot>, t: Throwable) {

                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }
}