package com.example.koin_apps


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.activity.LiveTimeViewModel

class LiveTimeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding
    private lateinit var liveTimeViewModel: LiveTimeViewModel
    private lateinit var vmFactory: ViewModelFactory

    private val bundle = Bundle()
    private val transactionFragment = TransactionFragment()
    private val orderBookFragment = OrderBookFragment()
    private val communityFragment = CommunityFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coinTitle = intent.getStringExtra("coinTitle")
        bundle.putString("coinTitle", coinTitle)

        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        liveTimeViewModel = ViewModelProvider(this, vmFactory)[LiveTimeViewModel::class.java]
        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)

        liveTimeBinding.coinTitle.text = coinTitle
        liveTimeViewModel.getTickerLive(coinTitle.toString())

        setContentView(liveTimeBinding.root)
    }

    override fun onResume() {
        super.onResume()
        liveTimeBinding.BtnTransaction.setOnClickListener(this)
        liveTimeBinding.BtnOrderBook.setOnClickListener(this)
        liveTimeBinding.BtnCommunity.setOnClickListener(this)

        transactionFragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FragmentLayout, transactionFragment)
            .commit()

        liveTimeViewModel.tickerLiveViewData.observe(this) {
            liveTimeBinding.tickerWon.text = getString(R.string.tickerWon, it.closing_price)
            liveTimeBinding.FlucatateRate24H.text =
                getString(R.string.ticker_Flucatate_rate24H, it.fluctate_rate_24H)
            liveTimeBinding.Flucatate24H.text =
                getString(R.string.ticker_Flucatate_won24H, it.fluctate_24H)
        }

    }

    override fun onClick(v: View?) {
        transactionFragment.arguments = bundle
        orderBookFragment.arguments = bundle
        communityFragment.arguments = bundle

        when (v?.id) {
            R.id.Btn_Transaction -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.FragmentLayout, transactionFragment)
                    .commit()
            }
            R.id.Btn_OrderBook -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.FragmentLayout, orderBookFragment)
                    .commit()
            }
            R.id.Btn_Community -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.FragmentLayout, communityFragment)
                    .commit()
            }
        }
    }

}