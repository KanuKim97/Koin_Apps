package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.activity.LiveTimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveTimeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding

    private val bundle = Bundle()
    private val transactionFragment = TransactionFragment()
    private val orderBookFragment = OrderBookFragment()
    private val communityFragment = CommunityFragment()
    private val liveTimeViewModel: LiveTimeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coinTitle = intent.getStringExtra("coinTitle").toString()

        bundle.putString("coinTitle", coinTitle)
        transactionFragment.arguments = bundle
        orderBookFragment.arguments = bundle
        communityFragment.arguments = bundle

        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)
        liveTimeBinding.coinTitle.text = coinTitle
        liveTimeViewModel.setTickerPath(coinTitle)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FragmentLayout, transactionFragment)
            .commit()

        setContentView(liveTimeBinding.root)
    }

    override fun onResume() {
        super.onResume()
        liveTimeBinding.BtnTransaction.setOnClickListener(this)
        liveTimeBinding.BtnOrderBook.setOnClickListener(this)
        liveTimeBinding.BtnCommunity.setOnClickListener(this)

        liveTimeViewModel.tickerLiveViewData.observe(this) {
            liveTimeBinding.tickerWon.text = getString(R.string.tickerWon, it.closing_price)
            liveTimeBinding.FlucatateRate24H.text =
                getString(R.string.ticker_Flucatate_rate24H, it.fluctate_rate_24H)
            liveTimeBinding.Flucatate24H.text =
                getString(R.string.ticker_Flucatate_won24H, it.fluctate_24H)
        }
    }

    override fun onClick(v: View?) {
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