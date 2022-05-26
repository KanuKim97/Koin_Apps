package com.example.koin_apps

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
    private var koinTradeInfo: TickerRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tradeActivityBinding = ActivityTradeBinding.inflate(layoutInflater)
        tradeViewModel = ViewModelProvider(this)[TradeViewModel::class.java]
        koinService = Common.KoinApiService_public

        setContentView(tradeActivityBinding.root)
    }

    override fun onResume() {
        super.onResume()

        if (tradeKoinName != null) {
            setThread(tradeKoinName)
        }

        tradeViewModel.tradeLiveData.observe(this, {
            Log.d("Trade Live Data", it.toString())
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
        koinTradeUrl.append("-")
        koinTradeUrl.append("KRW")

        return koinTradeUrl.toString()

    }

    private fun koinTransactionCall(koinName: String?){

        koinService.getKoinPrice(loadKoinTrade(koinName))
            .enqueue(object: Callback<TickerRoot> {
                override fun onResponse(call: Call<TickerRoot>, response: Response<TickerRoot>) {
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

                        val mKoinTradeInfo = mutableListOf<String>()

                        mKoinTradeInfo.add(koinTradeInfo?.data?.acc_trade_value_24H.toString())
                        mKoinTradeInfo.add(koinTradeInfo?.data?.prev_closing_price.toString())
                        mKoinTradeInfo.add(koinTradeInfo?.data?.fluctate_rate_24H.toString())

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