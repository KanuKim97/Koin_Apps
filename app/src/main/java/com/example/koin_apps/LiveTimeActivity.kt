package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.activity.LiveTimeViewModel

class LiveTimeActivity : AppCompatActivity() {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding
    private lateinit var vmFactory: ViewModelFactory
    private lateinit var liveTimeViewModel: LiveTimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        liveTimeViewModel = ViewModelProvider(this, vmFactory)[LiveTimeViewModel::class.java]
        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)

        setContentView(liveTimeBinding.root)
    }


}