package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.common.Constants
import com.example.koin_apps.databinding.ActivityTradeBinding
import com.example.koin_apps.viewModel.TradeViewModel
import java.lang.StringBuilder

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

        setThread()
    }

    override fun onRestart() {
        super.onRestart()

        setThread()
        Log.d("onRestarted", "onRestarted: " + threadNetwork?.isRunning)
    }

    override fun onStop() {
        super.onStop()

        interruptThread()
        Log.d("Thread interrupted", "All Thread is interrupted")
    }

    override fun onDestroy() {
        super.onDestroy()

        interruptThread()
        Log.d("Thread interrupted", "All Thread is interrupted")

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

    private fun setThread() {

        threadNetwork = NetworkingThread().apply {
            this.start()
            Log.d("NetWorkThread", "Thread is started")
        }

        threadSearch = NetworkingThread().apply {
            this.start()
            Log.d("SearchThread", "Thread is started")
        }

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

    /*

    private fun loadKoinTransaction(koinName: String?, countTransaction: Int): String {

        val koinTransactionUrl = StringBuilder(Constants.IKoinApiUri)

        koinTransactionUrl.append("transaction_history/")
        koinTransactionUrl.append(koinName)
        koinTransactionUrl.append("_")
        koinTransactionUrl.append("KRW")
        koinTransactionUrl.append("?count=${countTransaction}")

        return koinTransactionUrl.toString()
    }

    */
}