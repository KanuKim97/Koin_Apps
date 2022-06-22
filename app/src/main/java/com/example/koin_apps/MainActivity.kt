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
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivityMainBinding
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
        koinService = Common.KoinApiService_public

        setContentView(mainActivityBinding.root)
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.tickerLiveData.observe(
            this,
            { tickerMap ->

                if(tickerMap?.size != 0) {

                    mainActivityBinding.openPrice.text =
                        getString(
                            R.string.Ticker_Format,
                            tickerMap?.get("OpeningPrice"),
                            tickerMap?.get("ClosingPrice"),
                            tickerMap?.get("minTickerPrice"),
                            tickerMap?.get("maxTickerPrice"),
                            tickerMap?.get("TradeTickerUnits")
                        )

                } else {
                    Toast.makeText(applicationContext, "TickerDate is Empty", Toast.LENGTH_SHORT)
                        .show()
                }

            })

        mainActivityBinding.KoinSearchBtn.setOnClickListener(this)
        mainActivityBinding.nextPageBtn.setOnClickListener(this)
        mainActivityBinding.tradePageBtn.setOnClickListener(this)

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

    private fun tickerSearchCall(coinName: String){
        val mSearchTicker = RetrofitRepo.getTickerSingleton(coinName)

        mSearchTicker.enqueue(object: Callback<TickerRoot>{
            override fun onResponse(
                call: Call<TickerRoot>,
                response: Response<TickerRoot>
            ) {

                mTickerData = response.body()

                if(mTickerData == null || (mTickerData?.status != "0000")) {

                    Toast.makeText(
                        applicationContext,
                        R.string.API_DATA_Not_Founded,
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    val tickerKoinMap = mutableMapOf<String, Any?>()

                    tickerKoinMap["OpeningPrice"] = mTickerData?.data?.opening_price
                    tickerKoinMap["ClosingPrice"] = mTickerData?.data?.closing_price
                    tickerKoinMap["minTickerPrice"] = mTickerData?.data?.min_price
                    tickerKoinMap["maxTickerPrice"] = mTickerData?.data?.max_price
                    tickerKoinMap["TradeTickerUnits"] = mTickerData?.data?.units_traded

                    mainViewModel.updateKoinTicker(tickerKoinMap)

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