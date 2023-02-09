package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.koin_apps.databinding.ActivityLogoBinding
import com.example.koin_apps.viewModel.activity.LogoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoActivity : AppCompatActivity() {
    private lateinit var logoBinding: ActivityLogoBinding
    private val logoViewModel: LogoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logoBinding = ActivityLogoBinding.inflate(layoutInflater)

        logoViewModel.readAllData.observe(this) {
            if (it != null) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, SelectKoinActivity::class.java))
            }
        }

        setContentView(logoBinding.root)
    }
}