package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.viewModel.MainViewModel
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetrofitClient
import com.example.koin_apps.data.remote.RetrofitRepo
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
            {
                if(it?.status == "0000") {

                    mainActivityBinding.openPrice.text =
                        getString(
                            R.string.Ticker_Format,
                            it.opening_price,
                            it.closing_price,
                            it.min_price,
                            it.max_price,
                            it.units_traded
                        )

                } else { mainActivityBinding.openPrice.text = it?.errorMsg }

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
                Intent(this, OrderBookActivity::class.java).also {
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
                    200 -> mainViewModel.updateKoinTicker(response.body())

                    400 -> {
                        val jsonObject: JSONObject

                        try {
                            jsonObject = JSONObject(response.errorBody()!!.string())

                            val responseErrorCode = jsonObject.getString("status")
                            val responseErrorMsg = jsonObject.getString("message")

                            mainViewModel.updateErrorTicker(responseErrorCode, responseErrorMsg)
                        } catch (e: JSONException) { e.printStackTrace() }

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