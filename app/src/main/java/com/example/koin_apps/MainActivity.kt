package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.viewModel.MainViewModel
import com.example.koin_apps.common.Common
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.ticker.TickerList
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivityMainBinding
import kotlinx.coroutines.channels.ticker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var koinService: IKoinApiService

    var mKoin: TickerRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        koinService = Common.KoinApiService_public

        setContentView(mainActivityBinding.root)
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.tickerLiveData.observe(
            this,
            {
                val tickerMap = it

                if(tickerMap?.size != 0) {

                    mainActivityBinding.openPrice.text =
                        "OpenningPrice : " + tickerMap?.get("OpeningPrice") +
                                "\nClosingPrice : " + tickerMap?.get("ClosingPrice") +
                                "\nminTickerPrice : " + tickerMap?.get("minTickerPrice") +
                                "\nmaxTickerPrice : " + tickerMap?.get("maxTickerPrice") +
                                "\nTradeTickerUnits : " + tickerMap?.get("TradeTickerUnits")

                } else {
                    Toast.makeText(applicationContext, "TickerDate is Empty", Toast.LENGTH_SHORT)
                        .show()
                }

            })

        mainActivityBinding.KoinSearchBtn.setOnClickListener(this)
        mainActivityBinding.nextPageBtn.setOnClickListener(this)
        mainActivityBinding.tradePageBtn.setOnClickListener(this)

    }

    override fun onRestart() {
        super.onRestart()

    }


    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()

        mainActivityBinding.KoinInput.text?.clear()
    }

    override fun onClick(v: View?) {
        val coinTicker = mainActivityBinding.KoinInput.text.toString()

        when(v?.id) {
            R.id.KoinSearchBtn ->
                koinServiceCall(coinTicker)

            R.id.nextPageBtn ->
                Intent(this, LiveTimeActivity::class.java).also {

                    if(coinTicker.isNullOrEmpty()){

                        Toast.makeText(
                            applicationContext,
                            "InputKoinPlz",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        it.putExtra("KoinName", coinTicker)
                        startActivity(it)
                    }

                }

            R.id.tradePageBtn ->
                Intent(this, TradeActivity::class.java).also {

                    if(coinTicker.isNullOrEmpty()){

                        Toast.makeText(
                            applicationContext,
                            "InputKoinPlz",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        it.putExtra("KoinName", coinTicker)
                        startActivity(it)
                    }

                }

        }
    }

    private fun getKoinTickerUrl(coinTicker: String): String{

        val koinTickerUrl = StringBuilder(Constants.IKoinApiUri)

        koinTickerUrl.append("ticker/")
        koinTickerUrl.append(coinTicker)
        koinTickerUrl.append("_")
        koinTickerUrl.append("KRW")

        return koinTickerUrl.toString()
    }

    private fun koinServiceCall(coinTicker: String) {

        koinService.getKoinPrice(getKoinTickerUrl(coinTicker))
            .enqueue(object : Callback<TickerRoot> {
                override fun onResponse(
                    call: Call<TickerRoot>,
                    response: Response<TickerRoot>
                ) {

                    mKoin = response.body()

                    if (mKoin?.status == "0000"){

                        val tickerKoinList = mutableMapOf<String, Any?>()

                        tickerKoinList["OpeningPrice"] = mKoin?.data?.opening_price
                        tickerKoinList["ClosingPrice"] = mKoin?.data?.closing_price
                        tickerKoinList["minTickerPrice"] = mKoin?.data?.min_price
                        tickerKoinList["maxTickerPrice"] = mKoin?.data?.max_price
                        tickerKoinList["TradeTickerUnits"] = mKoin?.data?.units_traded

                        mainViewModel.updateKoinTicker(tickerKoinList)

                    } else {
                        Toast.makeText(applicationContext, "Failed to Import", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

                override fun onFailure(call: Call<TickerRoot>, t: Throwable) {
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}