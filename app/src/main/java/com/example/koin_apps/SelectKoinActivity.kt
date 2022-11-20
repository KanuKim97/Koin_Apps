package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.data.recyclerViewAdapter.SelectRecyclerAdapter
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.activity.SelectViewModel

class SelectKoinActivity : AppCompatActivity(){
    private lateinit var selectKoinBinding: ActivitySelectKoinBinding
    private lateinit var selectViewModel: SelectViewModel
    private lateinit var vmFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        selectViewModel = ViewModelProvider(this, vmFactory)[SelectViewModel::class.java]
        selectKoinBinding = ActivitySelectKoinBinding.inflate(layoutInflater)
        selectKoinBinding.CoinRecyclerView.layoutManager = LinearLayoutManager(this)

        setContentView(selectKoinBinding.root)
    }

    override fun onResume() {
        super.onResume()
        selectViewModel.getTicker()


        selectViewModel.coinList.observe(this) {
            selectKoinBinding.CoinRecyclerView.adapter = SelectRecyclerAdapter(it, selectViewModel)
        }

        selectViewModel.selectedCoin.observe(this) {
            if(it.size == 0) { selectKoinBinding.ListSelected.visibility = View.INVISIBLE }
            else { selectKoinBinding.ListSelected.text = it.toString() }
        }

        selectKoinBinding.compSelectBtn.setOnClickListener {
            selectViewModel.storeTitleData()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}