package com.example.koin_apps.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.common.Constants
import com.example.koin_apps.module.coroutineDispatcher.MainDispatcher
import com.example.koin_apps.presenter.adapter.recyclerViewAdapter.SelectRecyclerAdapter
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import com.example.koin_apps.presenter.viewModel.SelectViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class SelectKoinActivity : AppCompatActivity() {
    @MainDispatcher @Inject lateinit var mainDispatcher: CoroutineDispatcher

    private lateinit var selectRecyclerAdapter: SelectRecyclerAdapter
    private val selectKoinBinding by lazy { ActivitySelectKoinBinding.inflate(layoutInflater) }
    private val selectViewModel: SelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        updateTickerList()

        selectKoinBinding.compSelectBtn.setOnClickListener {
            storeTicker()
            Log.d(Constants.LOG_TAG, "${getSelectedItems()}")
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

    private fun getSelectedItems() = selectRecyclerAdapter.getSelectedItems()

    private fun storeTicker(): Job =
        selectViewModel.storeTickerTitle(selectRecyclerAdapter.getSelectedItems())

}