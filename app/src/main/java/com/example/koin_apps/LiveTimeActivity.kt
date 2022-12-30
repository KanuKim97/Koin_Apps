package com.example.koin_apps

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.activity.LiveTimeViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LiveTimeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding
    private lateinit var liveTimeViewModel: LiveTimeViewModel
    private lateinit var vmFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        liveTimeViewModel = ViewModelProvider(this, vmFactory)[LiveTimeViewModel::class.java]
        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)

        setContentView(liveTimeBinding.root)
    }

    override fun onResume() {
        super.onResume()
        val coinTitle = intent.getStringExtra("coinTitle")
        liveTimeBinding.coinTitle.text = coinTitle

        liveTimeViewModel.getTickerLive(coinTitle.toString())

        liveTimeViewModel.tickerLiveViewData.observe(this) {
            liveTimeBinding.tickerWon.text = it.closing_price
            liveTimeBinding.FlucatateRate24H.text = it.fluctate_rate_24H
            liveTimeBinding.Flucatate24H.text = it.fluctate_24H
        }

        liveTimeBinding.BtnTransaction.setOnClickListener(this)
        liveTimeBinding.BtnOrderBook.setOnClickListener(this)
        liveTimeBinding.BtnCommunity.setOnClickListener(this)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FragmentLayout, TransactionFragment())
            .commit()
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.Btn_Transaction -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.FragmentLayout, TransactionFragment())
                    .commit()
            }
            R.id.Btn_OrderBook -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.FragmentLayout, OrderBookFragment())
                    .commit()
            }
            R.id.Btn_Community -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.FragmentLayout, CommunityFragment())
                    .commit()
            }
        }
    }

}