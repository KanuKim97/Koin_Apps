package com.example.koin_apps.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TickerInfoActivity : AppCompatActivity() {
    private val tickerInfoBinding by lazy { ActivityLiveTimeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(tickerInfoBinding.root)
    }
}

