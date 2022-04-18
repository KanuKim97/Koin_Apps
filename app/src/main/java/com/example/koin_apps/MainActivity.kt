package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.viewModel.MainViewModel
import com.example.koin_apps.common.Common
import com.example.koin_apps.common.Constants
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.model.Root
import com.example.koin_apps.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var koinService: IKoinApiService
    private lateinit var mainViewModel: MainViewModel

    var mKoin: Root? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        koinService = Common.KoinApiService_public
        mainViewModel = ViewModelProvider(this)
            .get(MainViewModel::class.java)

    }


    override fun onResume() {
        super.onResume()

        mainViewModel.resultKoinValue.observe(
            this,
            Observer {
                mainActivityBinding.openPrice.text = it.toString()
            })

        mainActivityBinding.KoinSearchBtn.setOnClickListener(this)
        mainActivityBinding.nextPageBtn.setOnClickListener(this)
    }

    
    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.KoinSearchBtn ->
                koinServiceCall(coinTicker = mainActivityBinding.KoinInput.text.toString())

            R.id.nextPageBtn ->
                startActivity(Intent(this, LiveTime::class.java))
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
                    mainViewModel.updateValue(input = mKoin?.data?.opening_price)
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    Log.d("Failed","${t.message}")
                }

            })
    }
}