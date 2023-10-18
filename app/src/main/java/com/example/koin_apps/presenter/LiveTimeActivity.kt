package com.example.koin_apps.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.koin_apps.R
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.presenter.viewModel.LiveTimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveTimeActivity : AppCompatActivity() {
    private val liveTimeBinding by lazy { ActivityLiveTimeBinding.inflate(layoutInflater) }
    private val liveTimeViewModel: LiveTimeViewModel by viewModels()

    private val orderBookFragment: OrderBookFragment = OrderBookFragment()
    private val fragmentBundle: Bundle = Bundle()
    private val ticker: String by lazy { setTicker() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveTimeViewModel.loadTickerInfo(ticker)
        setFragmentBundle()

        updateTickerTitle()
        updateTickerInfo()

        supportFragmentManager.beginTransaction()
            .replace(R.id.FragmentLayout, orderBookFragment)
            .commit()

        setContentView(liveTimeBinding.root)
    }

    private fun setTicker(): String = intent.getStringExtra("coinTitle").toString()

    private fun setFragmentBundle() {
        fragmentBundle.putString("coinTitle", ticker)
        orderBookFragment.arguments = fragmentBundle
    }

    private fun updateTickerTitle() { liveTimeBinding.coinTitle.text = ticker }

    private fun updateTickerInfo() = liveTimeViewModel.tickerLiveViewData.observe(this) {
        liveTimeBinding.tickerWon.text = getString(R.string.tickerWon, it.closingPrice.toString())
        liveTimeBinding.Flucatate24H.text = getString(R.string.ticker_Flucatate_won24H, it.fluctate_24H.toString())
        liveTimeBinding.FlucatateRate24H.text = getString(R.string.ticker_Flucatate_rate24H, it.fluctate_Rate.toString())
    }

}

