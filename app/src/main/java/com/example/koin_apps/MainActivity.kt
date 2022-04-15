package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.koin_application.viewModel.MainViewModel
import com.example.koin_apps.common.Common
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.Root
import com.example.koin_apps.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var koinService: IKoinApiService
    private lateinit var mainViewModel: MainViewModel

    var mKoin: Root? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        koinService = Common.KoinApiService_public
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()

        mainActivityBinding.KoinSearchBtn.setOnClickListener {
            koinServiceCall(coinTicker = mainActivityBinding.KoinInput.text.toString())
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
            .enqueue(object : Callback<Root> {
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    mKoin = response.body()
                    Log.d("mKoin_Val",mKoin?.data?.opening_price.toString())
                    mainActivityBinding.openPrice.text = mKoin?.data?.opening_price.toString()

                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    Log.d("Failed","${t.message}")
                }

            })
    }
}