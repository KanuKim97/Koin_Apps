package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.activity.LiveTimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveTimeActivity : AppCompatActivity() {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding
    private val liveTimeViewModel: LiveTimeViewModel by viewModels()
    private val orderBookFragment = OrderBookFragment()
    private val fragmentBundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)

        val coinTitle: String = intent.getStringExtra("coinTitle").toString()
        fragmentBundle.putString("coinTitle", coinTitle)
        orderBookFragment.arguments = fragmentBundle

        liveTimeViewModel.loadTickerInfo(coinTitle)

        liveTimeBinding.coinTitle.text = coinTitle
        liveTimeViewModel.tickerLiveViewData.observe(this) {
            liveTimeBinding.tickerWon.text = getString(R.string.tickerWon, it.closing_price)
            liveTimeBinding.Flucatate24H.text = getString(R.string.ticker_Flucatate_won24H, it.fluctate_24H)
            liveTimeBinding.FlucatateRate24H.text = getString(R.string.ticker_Flucatate_rate24H, it.fluctate_rate_24H)
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FragmentLayout, orderBookFragment)
            .commit()

        setContentView(liveTimeBinding.root)
    }
}