package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.di.coroutineDispatcher.MainDispatcher
import com.example.koin_apps.data.recyclerViewAdapter.SelectRecyclerAdapter
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import com.example.koin_apps.viewModel.activity.SelectViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SelectKoinActivity : AppCompatActivity() {
    @MainDispatcher @Inject lateinit var mainDispatcher: CoroutineDispatcher
    private lateinit var selectRecyclerAdapter: SelectRecyclerAdapter
    private val selectKoinBinding by lazy { ActivitySelectKoinBinding.inflate(layoutInflater) }
    private val selectViewModel: SelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectKoinBinding.CoinRecyclerView.layoutManager = LinearLayoutManager(this)

        updateTickerList()

        selectKoinBinding.compSelectBtn.setOnClickListener {
            storeTicker()
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContentView(selectKoinBinding.root)
    }

    private fun updateTickerList() = selectViewModel.tickerList.observe(this) { result ->
        lifecycleScope.launch(mainDispatcher) {
            selectRecyclerAdapter = SelectRecyclerAdapter(result)
            selectKoinBinding.CoinRecyclerView.adapter = selectRecyclerAdapter
        }
    }

    private fun storeTicker(): Job =
        selectViewModel.storeTickerTitle(selectRecyclerAdapter.getSelectedItems())

}