package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.databinding.ActivityTradeBinding
import com.example.koin_apps.viewModel.TradeViewModel

class TradeActivity : AppCompatActivity() {
    private lateinit var tradeActivityBinding: ActivityTradeBinding
    private lateinit var tradeViewModel: TradeViewModel

    private var threadNetwork: NetworkingThread? = null
    private var threadSearch: NetworkingThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tradeActivityBinding = ActivityTradeBinding.inflate(layoutInflater)
        tradeViewModel = ViewModelProvider(this)[TradeViewModel::class.java]

        setContentView(tradeActivityBinding.root)
    }

    override fun onResume() {
        super.onResume()
        val coinName = intent.getStringExtra("coinName")
        val coinCount = intent.getStringExtra("count")


        if (coinName != null) {
            setThread(coinName)
        }

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    inner class NetworkingThread(): Thread() {
        var isRunning = true

        override fun run() {

            while (isRunning){

                try {
                    Log.d("Network Thread", "is Running!")
                    sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }

        }

    }

    private fun setThread(search_Coin: String){
        if(search_Coin.isEmpty())
            Log.d("Search", "Search is null")
            interruptThread()

    }

    private fun interruptThread(){

        threadNetwork?.run {
            this.isRunning = false
            if (!this.isInterrupted)
                this.interrupt()
        }

        threadSearch?.run{
            this.isRunning = false
            if (!this.isInterrupted)
                this.interrupt()
        }

    }
}