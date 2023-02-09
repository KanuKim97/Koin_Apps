package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.recyclerViewAdapter.SelectRecyclerAdapter
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import com.example.koin_apps.viewModel.activity.SelectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectKoinActivity : AppCompatActivity(){
    private lateinit var selectKoinBinding: ActivitySelectKoinBinding
    private lateinit var selectRecyclerAdapter: SelectRecyclerAdapter
    private val selectViewModel: SelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectKoinBinding = ActivitySelectKoinBinding.inflate(layoutInflater)
        selectKoinBinding.CoinRecyclerView.layoutManager = LinearLayoutManager(this)

        selectViewModel.getCoinTitle()
        selectViewModel.coinTitleList.observe(this) {
            selectRecyclerAdapter = SelectRecyclerAdapter(it)
            selectKoinBinding.CoinRecyclerView.adapter = selectRecyclerAdapter
        }

        selectKoinBinding.compSelectBtn.setOnClickListener {
            selectViewModel.storeCoinTitle(selectRecyclerAdapter.getSelectedItems())
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContentView(selectKoinBinding.root)
    }

}