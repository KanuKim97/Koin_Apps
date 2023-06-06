package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.koin_apps.data.di.coroutineDispatcher.MainDispatcher
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.activity.LiveTimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LiveTimeActivity : AppCompatActivity() {
    @MainDispatcher @Inject lateinit var mainDispatcher: CoroutineDispatcher
    private val liveTimeBinding by lazy { ActivityLiveTimeBinding.inflate(layoutInflater) }
    private val liveTimeViewModel: LiveTimeViewModel by viewModels()
    private val orderBookFragment = OrderBookFragment()
    private val fragmentBundle = Bundle()
    private val ticker: String by lazy { setTicker() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveTimeViewModel.loadTickerInfo(ticker)
        setFragmentBundle()

        updateTickerTitle()
        updateTickerInfo()

        supportFragmentManager
            .beginTransaction()
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
        lifecycleScope.launch(mainDispatcher) {
            liveTimeBinding.tickerWon.text =
                getString(R.string.tickerWon, it.closing_price)
            liveTimeBinding.Flucatate24H.text =
                getString(R.string.ticker_Flucatate_won24H, it.fluctate_24H)
            liveTimeBinding.FlucatateRate24H.text =
                getString(R.string.ticker_Flucatate_rate24H, it.fluctate_rate_24H)
        }
    }

}

