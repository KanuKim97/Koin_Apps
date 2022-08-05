package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.koin_apps.databinding.ActivityLogoBinding

class LogoActivity : AppCompatActivity() {
    private lateinit var logoBinding: ActivityLogoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logoBinding = ActivityLogoBinding.inflate(layoutInflater)
        setContentView(logoBinding.root)
    }

    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed(
            { startActivity(Intent(this, MainActivity::class.java)) },
            5000)

    }

}