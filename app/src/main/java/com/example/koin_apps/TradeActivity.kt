package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.remote.RetrofitClient
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivityTradeBinding
import com.example.koin_apps.viewModel.TradeViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TradeActivity : AppCompatActivity() {
    private lateinit var tradeActivityBinding: ActivityTradeBinding
    private lateinit var tradeViewModel: TradeViewModel
    private lateinit var koinService: IKoinApiService

    private var threadNetwork: NetworkingThread? = null

    var mTradeCoinData: TickerRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tradeActivityBinding = ActivityTradeBinding.inflate(layoutInflater)
        tradeViewModel = ViewModelProvider(this)[TradeViewModel::class.java]
        koinService = RetrofitClient.koinApiService_Public

        setContentView(tradeActivityBinding.root)
    }


    override fun onResume() {
        super.onResume()

        val tradeKoinName = intent.getStringExtra("KoinName")
        if (tradeKoinName != null) { setThread(tradeKoinName) }
        else {

            Toast.makeText(
                applicationContext,
                R.string.Empty_Coin_TxtBox,
                Toast.LENGTH_SHORT
            ).show()

            startActivity(Intent(this, MainActivity::class.java))

        }

        tradeViewModel.tradeLiveData.observe(
            this,
            { tradeMap ->

                tradeActivityBinding.showTradedUnits.text =
                    getString(
                        R.string.Trade_Format,
                        tradeKoinName,
                        tradeMap?.get("Prev_Closing_Price").toString(),
                        tradeMap?.get("TradeValue").toString(),
                        tradeMap?.get("Fluctated_24H").toString()
                    )

        })
    }


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
        private val tradeCoinName = tradeKoinName
        var isRunning: Boolean = true

        override fun run() {

            while (isRunning){

                try {

                    tradeCoinResponse(tradeCoinName)
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


    private fun tradeCoinResponse(CoinName: String){
        val mTradeTicker = RetrofitRepo.getTickerSingleton(CoinName)

        mTradeTicker.enqueue(object: Callback<TickerRoot>{
            override fun onResponse(
                call: Call<TickerRoot>,
                response: Response<TickerRoot>
            ) {
            /*
                if(response.isSuccessful) {

                    mTradeCoinData = response.body()

                    if (mTradeCoinData?.status != "0000") {

                        Log.d("Status: ", mTradeCoinData?.status.toString())
                        Log.d("Message: ", mTradeCoinData?.message.toString())

                        interruptThread()

                    }

                }
            */

                when(response.code()) {

                    200 -> {

                        mTradeCoinData = response.body()

                        val mTradeData = mutableMapOf<String, Any?>()

                        mTradeData["Status"] = mTradeCoinData?.status
                        mTradeData["TradeValue"] = mTradeCoinData?.data?.acc_trade_value_24H
                        mTradeData["Prev_Closing_Price"] = mTradeCoinData?.data?.prev_closing_price
                        mTradeData["Fluctated_24H"] = mTradeCoinData?.data?.fluctate_rate_24H

                        tradeViewModel.updateKoinTrade(mTradeData)

                    }

                    400 -> {

                        val jsonObject: JSONObject
                        val mTradeErrorBody = mutableMapOf<String, Any?>()

                        try {

                            jsonObject = JSONObject(response.errorBody()!!.string())

                            val responseCode = jsonObject.getString("status")
                            val responseMsg = jsonObject.getString("message")

                            mTradeErrorBody["Status"] = responseCode
                            mTradeErrorBody["Message"] = responseMsg

                            tradeViewModel.updateKoinTrade(mTradeErrorBody)

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }

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