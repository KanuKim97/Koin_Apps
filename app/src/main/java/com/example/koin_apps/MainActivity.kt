package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                val TickerClass = it?.get(0)

                if(TickerClass == null){
                    mainActivityBinding.openPrice.text = "Coin is Not Selected"
                } else {
                    mainActivityBinding.openPrice.text =
                        "개장가 : " + TickerClass.openTickerPrice +
                                "\n종장가 : " + TickerClass.closeTickerPrice +
                                "\n최저가 : "+ TickerClass.minTickerPrice +
                                "\n최대가 : "+ TickerClass.maxTickerPrice +
                                "\n거래량 : "+ TickerClass.tickerTradedUnits

                }
            }
        )

        mainActivityBinding.KoinSearchBtn.setOnClickListener(this)
        mainActivityBinding.nextPageBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        mainActivityBinding.KoinInput.text?.clear()
        mainViewModel.run {

        }
    }

    override fun onClick(v: View?) {
        val coinTicker = mainActivityBinding.KoinInput.text.toString()

        when(v?.id) {
            R.id.KoinSearchBtn ->
                koinServiceCall(coinTicker)

            R.id.nextPageBtn ->
                Intent(this, LiveTimeActivity::class.java).also {
                    if(coinTicker.isNullOrEmpty()){
                        Toast.makeText(applicationContext, "InputKoinPlz", Toast.LENGTH_SHORT)
                            .show()

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
                override fun onResponse(call: Call<TickerRoot>, response: Response<TickerRoot>) {
                    mKoin = response.body()

                    if (mKoin?.status == "0000"){

                        val tickerKoinList = TickerList(
                            mKoin?.data?.opening_price!!,
                            mKoin?.data?.closing_price!!,
                            mKoin?.data?.min_price!!,
                            mKoin?.data?.max_price!!,
                            mKoin?.data?.units_traded!!,
                            mKoin?.data?.acc_trade_value!!,
                            mKoin?.data?.prev_closing_price!!,
                            mKoin?.data?.units_traded_24H!!,
                            mKoin?.data?.acc_trade_value_24H!!,
                            mKoin?.data?.fluctate_24H!!,
                            mKoin?.data?.fluctate_rate_24H!!,
                            mKoin?.data?.date!! )

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