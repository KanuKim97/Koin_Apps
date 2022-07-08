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
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetrofitClient
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.requestError.RequestErrorRoot
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var koinService: IKoinApiService

    var mTickerData: TickerRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        koinService = RetrofitClient.koinApiService_Public

        setContentView(mainActivityBinding.root)
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.tickerLiveData.observe(
            this,
            { tickerMap ->

                if(tickerMap?.get("Status") == "0000") {

                    mainActivityBinding.openPrice.text =
                        getString(
                            R.string.Ticker_Format,
                            tickerMap.get("OpeningPrice"),
                            tickerMap.get("ClosingPrice"),
                            tickerMap.get("minTickerPrice"),
                            tickerMap.get("maxTickerPrice"),
                            tickerMap.get("TradeTickerUnits")
                        )

                } else {

                    mainActivityBinding.openPrice.text =
                        tickerMap?.get("Message").toString()

                }

            })

        mainActivityBinding.KoinSearchBtn.setOnClickListener(this)
        mainActivityBinding.nextPageBtn.setOnClickListener(this)
        mainActivityBinding.tradePageBtn.setOnClickListener(this)
        mainActivityBinding.OrderBookBtn.setOnClickListener(this)

    }


    override fun onDestroy() {
        super.onDestroy()
        mainActivityBinding.KoinInput.text?.clear()
    }

    override fun onClick(v: View?) {
        val coinTicker = mainActivityBinding.KoinInput.text.toString()

        when(v?.id) {
            R.id.KoinSearchBtn ->
                tickerSearchCall(coinTicker)

            R.id.nextPageBtn ->
                Intent(this, LiveTimeActivity::class.java).also {

                    if(coinTicker.isNullOrEmpty()){

                        Toast.makeText(
                            applicationContext,
                            R.string.Empty_Coin_TxtBox,
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
                            R.string.Empty_Coin_TxtBox,
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        it.putExtra("KoinName", coinTicker)
                        startActivity(it)

                    }

                }

            R.id.OrderBookBtn ->
                startActivity(Intent(this, OrderBookActivity::class.java))

        }
    }

    private fun tickerSearchCall(coinName: String){

        val mSearchTicker = RetrofitRepo.getTickerSingleton(coinName)

        mSearchTicker.enqueue(object: Callback<TickerRoot>{
            override fun onResponse(
                call: Call<TickerRoot>,
                response: Response<TickerRoot>
            ) {

                when(response.code()) {

                    200 -> {

                        val tickerKoinMap = mutableMapOf<String, Any?>()

                        mTickerData = response.body()

                        tickerKoinMap["Status"] = mTickerData?.status
                        tickerKoinMap["OpeningPrice"] = mTickerData?.data?.opening_price
                        tickerKoinMap["ClosingPrice"] = mTickerData?.data?.closing_price
                        tickerKoinMap["minTickerPrice"] = mTickerData?.data?.min_price
                        tickerKoinMap["maxTickerPrice"] = mTickerData?.data?.max_price
                        tickerKoinMap["TradeTickerUnits"] = mTickerData?.data?.units_traded

                        mainViewModel.updateKoinTicker(tickerKoinMap)

                    }

                    400 -> {

                        val jsonObject: JSONObject
                        val errorTickerBody = mutableMapOf<String, Any?>()

                        try {

                            jsonObject = JSONObject(response.errorBody()!!.string())

                            val responseCode = jsonObject.getString("status")
                            val responseMsg = jsonObject.getString("message")

                            errorTickerBody["Status"] = responseCode
                            errorTickerBody["Message"] = responseMsg

                            mainViewModel.updateKoinTicker(errorTickerBody)

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