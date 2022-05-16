package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.koin_apps.databinding.ActivityTradeBinding

class TradeActivity : AppCompatActivity() {
    private lateinit var tradeActivityBinding: ActivityTradeBinding

    private var threadNetwork: NetworkingThread? = null
    private var threadSearch: NetworkingThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tradeActivityBinding = ActivityTradeBinding.inflate(layoutInflater)
        setContentView(tradeActivityBinding.root)

    }

    override fun onResume() {
        super.onResume()
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

    private fun setThread(){

        if (threadNetwork == null) {
            threadSearch = NetworkingThread().apply { this.start() }
        }

        if (threadSearch == null) {
            threadNetwork = NetworkingThread().apply { this.start() }
        }


    }

    /**
     *  thread Interrupt
     *
    private fun interruptThread(){

        threadNetwork = threadNetwork?.run{
            this.isRunning = false
            if(!this.isInterrupted)
                this.interrupt()
        }

        threadSearch = threadSearch?.run{
            this.isRunning = false
            if (!this.isInterrupted)
                this.interrupt()
        }

    }
    **/

}