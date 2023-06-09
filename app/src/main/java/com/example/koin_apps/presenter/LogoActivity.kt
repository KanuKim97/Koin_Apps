package com.example.koin_apps.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.koin_apps.databinding.ActivityLogoBinding
import com.example.koin_apps.presenter.viewModel.LogoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoActivity : AppCompatActivity() {
    private val logoBinding by lazy { ActivityLogoBinding.inflate(layoutInflater) }
    private val logoViewModel: LogoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logoViewModel.readAllTicker.observe(this) { tickerList ->
            if (tickerList != null) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, SelectKoinActivity::class.java))
                finish()
            }
        }

        setContentView(logoBinding.root)
    }

}