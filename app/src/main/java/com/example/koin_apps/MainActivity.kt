package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.viewModel.activity.MainViewModel
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetroRepo
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivityMainBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var vmFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var koinService: IKoinApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        mainViewModel = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]

        setContentView(mainActivityBinding.root)
    }

    override fun onResume() {
        super.onResume()
        /*
            Todo: Observe ViewModel Livedata
         */
        mainActivityBinding.KoinSearchBtn.setOnClickListener(this)
        mainActivityBinding.nextPageBtn.setOnClickListener(this)
        mainActivityBinding.tradePageBtn.setOnClickListener(this)
        mainActivityBinding.OrderBookBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityBinding.TxtInputCoin.text?.clear()
    }

    override fun onClick(v: View?) {
        val tickerTitle = mainActivityBinding.TxtInputCoin.text.toString()

        when(v?.id) {
            R.id.KoinSearchBtn -> mainViewModel.getTicker(tickerTitle)

            R.id.nextPageBtn ->
                Intent(this, LiveTimeActivity::class.java).also {
                    if(tickerTitle.isNullOrEmpty()){
                        Toast.makeText(
                            applicationContext,
                            R.string.Empty_Coin_TxtBox,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        it.putExtra("CoinTitle", tickerTitle)
                        startActivity(it)
                    }
                }

            R.id.tradePageBtn ->
                Intent(this, TradeActivity::class.java).also {
                    if(tickerTitle.isNullOrEmpty()){
                        Toast.makeText(
                            applicationContext,
                            R.string.Empty_Coin_TxtBox,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        it.putExtra("CoinTitle", tickerTitle)
                        startActivity(it)
                    }
                }

            R.id.OrderBookBtn ->
                Intent(this, OrderBookActivity::class.java).also {
                    if(tickerTitle.isNullOrEmpty()){
                        Toast.makeText(
                            applicationContext,
                            R.string.Empty_Coin_TxtBox,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        it.putExtra("CoinTitle", tickerTitle)
                        startActivity(it)
                    }
                }

        }
    }

}