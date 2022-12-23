package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.databinding.ActivityLogoBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.activity.LogoViewModel

class LogoActivity : AppCompatActivity() {
    private lateinit var logoBinding: ActivityLogoBinding
    private lateinit var logoViewModel: LogoViewModel
    private lateinit var vmFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        logoViewModel = ViewModelProvider(this, vmFactory)[LogoViewModel::class.java]
        logoBinding = ActivityLogoBinding.inflate(layoutInflater)
        setContentView(logoBinding.root)
    }

    override fun onResume() {
        super.onResume()
        logoViewModel.readAllData.observe(this) {
            if (it != null) { startActivity(Intent(this, MainActivity::class.java)) }
            else { startActivity(Intent(this, SelectKoinActivity::class.java)) }
        }
    }
}