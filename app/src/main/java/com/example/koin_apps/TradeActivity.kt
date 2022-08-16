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

        tradeViewModel.tradeLiveData.observe(this, {
            if(it?.status == "0000") {
                tradeActivityBinding.showTradedUnits.text =
                    getString(
                        R.string.Trade_Format,
                        tradeKoinName,
                        it.prev_closing_price,
                        it.acc_trade_value_24H,
                        it.fluctate_rate_24H
                    )
            } else { tradeActivityBinding.showTradedUnits.text = it?.errorMsg }
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

                when(response.code()) {

                    200 -> tradeViewModel.updateKoinTrade(response.body())

                    400 -> {
                        val jsonObject: JSONObject

                        try {
                            jsonObject = JSONObject(response.errorBody()!!.string())

                            val responseCode = jsonObject.getString("status")
                            val responseMsg = jsonObject.getString("message")

                            tradeViewModel.updateErrorTicker(responseCode, responseMsg)
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