package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.databinding.ActivityLiveTimeBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.activity.LiveTimeViewModel
import java.lang.NumberFormatException

class LiveTimeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var liveTimeBinding: ActivityLiveTimeBinding
    private lateinit var vmFactory: ViewModelFactory
    private lateinit var liveTimeViewModel: LiveTimeViewModel

    private var toggleValue: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        liveTimeViewModel = ViewModelProvider(this, vmFactory)[LiveTimeViewModel::class.java]
        liveTimeBinding = ActivityLiveTimeBinding.inflate(layoutInflater)

        setContentView(liveTimeBinding.root)
    }

    override fun onResume() {
        super.onResume()

        /*
            Todo: Observe ViewModel Livedata
         */

        liveTimeBinding.getBackBtn.setOnClickListener(this)
        liveTimeBinding.BtnTransaction.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        liveTimeBinding.countTransaction.text?.clear()
    }

    override fun onClick(v: View?) {
        val coinTitle = intent.getStringExtra("CoinTitle").toString()
        val cntTransaction =
            try { liveTimeBinding.countTransaction.text.toString().toInt() }
            catch (e:NumberFormatException) { 0 }

        when(v?.id) {
            R.id.getBackBtn ->
                startActivity(Intent(this, MainActivity::class.java))


        }
    }


}