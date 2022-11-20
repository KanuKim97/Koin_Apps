package com.example.koin_apps


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.databinding.ActivityTradeBinding
import com.example.koin_apps.viewModel.activity.TradeViewModel


class TradeActivity : AppCompatActivity() {
    private lateinit var tradeActivityBinding: ActivityTradeBinding
    private lateinit var tradeViewModel: TradeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tradeActivityBinding = ActivityTradeBinding.inflate(layoutInflater)
        tradeViewModel = ViewModelProvider(this)[TradeViewModel::class.java]

        setContentView(tradeActivityBinding.root)
    }


    override fun onResume() {
        super.onResume()

    }


/*    private fun tradeCoinResponse(CoinName: String){
        val mTradeTicker = RetroRepo.getTickerSingleton(CoinName)

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
    }*/
}