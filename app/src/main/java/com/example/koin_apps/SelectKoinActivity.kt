package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.data.recyclerViewAdapter.RecyclerViewAdapter
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.activity.SelectViewModel

class SelectKoinActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var selectKoinBinding: ActivitySelectKoinBinding
    private lateinit var vmFactory: ViewModelFactory
    private lateinit var selectViewModel: SelectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        selectViewModel = ViewModelProvider(this, vmFactory)[SelectViewModel::class.java]
        selectKoinBinding = ActivitySelectKoinBinding.inflate(layoutInflater)
        setContentView(selectKoinBinding.root)

        selectKoinBinding.CoinRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        selectViewModel.getTicker()
        selectViewModel.selectKoinList.observe(this) {
            selectKoinBinding.CoinRecyclerView.adapter = RecyclerViewAdapter(it)
        }

        selectKoinBinding.compSelectBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.compSelectBtn -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        }
    }


}