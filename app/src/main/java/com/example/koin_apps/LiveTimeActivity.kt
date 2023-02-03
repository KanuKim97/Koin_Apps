package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.activity.LiveTimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveTimeActivity : AppCompatActivity() {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding

    private val bundle = Bundle()
    private val orderBookFragment = OrderBookFragment()
    private val liveTimeViewModel: LiveTimeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val coinTitle = intent.getStringExtra("coinTitle").toString()

        bundle.putString("coinTitle", coinTitle)
        orderBookFragment.arguments = bundle

        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)
        liveTimeBinding.coinTitle.text = coinTitle
        liveTimeViewModel.setTickerPath(coinTitle)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FragmentLayout, orderBookFragment)
            .commit()

        setContentView(liveTimeBinding.root)
    }

    override fun onResume() {
        super.onResume()

        liveTimeViewModel.tickerLiveViewData.observe(this) {
            liveTimeBinding.tickerWon.text = getString(R.string.tickerWon, it.closing_price)
            liveTimeBinding.FlucatateRate24H.text =
                getString(R.string.ticker_Flucatate_rate24H, it.fluctate_rate_24H)
            liveTimeBinding.Flucatate24H.text =
                getString(R.string.ticker_Flucatate_won24H, it.fluctate_24H)
        }
    }

}