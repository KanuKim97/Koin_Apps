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
import com.example.koin_apps.data.remote.model.ticker.TickerList
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var koinService: IKoinApiService
    private lateinit var mainViewModel: MainViewModel
    private val tickerArray = arrayListOf<TickerList>()

    var mKoin: TickerRoot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        koinService = Common.KoinApiService_public
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()

        /*
        mainViewModel.resultKoinValue.observe(
            this,
            {
                mainActivityBinding.openPrice.text = it.toString()
            })
         */


        mainActivityBinding.KoinSearchBtn.setOnClickListener(this)
        mainActivityBinding.nextPageBtn.setOnClickListener(this)
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
                    it.putExtra("KoinName", coinTicker)
                    startActivity(it)
                }

        }
    }

    private fun getKoinTickerUrl(coinTicker: String): String{
        val koinTickerUrl = StringBuilder("https://api.bithumb.com/public/ticker/")
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

                        // mainViewModel.updateValue(input = mKoin?.data?.opening_price!!)
                        // Log.d("mKoin body","${mKoin?.status} \n ${mKoin?.data} \n ${mKoin?.message}")

                        //TickerList Add
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

                        //Log.d("dataClass","$tickerKoinList")
                        tickerArray.add(tickerKoinList)


                    } else {
                        Log.d("mKoin Failed Status","${mKoin?.status}")

                        /*
                        ToDo (
                         Exception Processing Needed
                         Example)
                         KoinName : "AAA"
                         mKoin.Status : "null" )

                        println(mKoin?.status.toString())
                        println(Constants.status[mKoin?.status])
                        */
                    }

                }

                override fun onFailure(call: Call<TickerRoot>, t: Throwable) {
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}