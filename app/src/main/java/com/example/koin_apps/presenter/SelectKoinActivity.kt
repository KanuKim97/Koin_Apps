package com.example.koin_apps.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.presenter.adapter.recyclerViewAdapter.SelectRecyclerAdapter
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import com.example.koin_apps.presenter.viewModel.SelectViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class SelectKoinActivity : AppCompatActivity() {
    private val selectKoinBinding by lazy { ActivitySelectKoinBinding.inflate(layoutInflater) }
    private val selectViewModel: SelectViewModel by viewModels()

    private lateinit var selectRecyclerAdapter: SelectRecyclerAdapter
    private val selectedTickerList: List<String> by lazy { selectRecyclerAdapter.getSelectedItems() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        updateTickerList()


        selectKoinBinding.compSelectBtn.setOnClickListener {
            storeTicker(selectedTickerList)
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContentView(selectKoinBinding.root)
    }

    private fun initRecyclerView() = selectKoinBinding.CoinRecyclerView.apply {
        layoutManager = LinearLayoutManager(this@SelectKoinActivity)
        setHasFixedSize(true)
    }

    private fun updateTickerList() = selectViewModel.tickerList.observe(this) { result ->
        selectRecyclerAdapter = SelectRecyclerAdapter(result)
        selectKoinBinding.CoinRecyclerView.adapter = selectRecyclerAdapter
    }

    private fun storeTicker(selectedTickers: List<String>): Job =
        selectViewModel.storeTickerTitle(selectedTickers)
}