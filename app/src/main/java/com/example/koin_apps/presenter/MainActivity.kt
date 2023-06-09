package com.example.koin_apps.presenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.R
import com.example.koin_apps.presenter.adapter.recyclerViewAdapter.MainRecyclerAdapter
import com.example.koin_apps.presenter.viewModel.MainViewModel
import com.example.koin_apps.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var alertDialog: AlertDialog.Builder

    private val mainActivityBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainRecyclerView by lazy { mainActivityBinding.mainRecyclerView }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchTickerDB()
        initRecyclerView()

        mainViewModel.tickerAllData.observe(this) { tickerList ->
            if (tickerList != null) {
                mainRecyclerView.adapter = MainRecyclerAdapter(this, tickerList)
            } else {
                showDataNullDialog()
            }
        }

        mainActivityBinding.addCoinBtn.setOnClickListener {
            startActivity(Intent(this, SelectKoinActivity::class.java))
        }

        setContentView(mainActivityBinding.root)
    }

    private fun fetchTickerDB(): Job = mainViewModel.fetchAllTickerData()

    private fun initRecyclerView(): RecyclerView = mainActivityBinding.mainRecyclerView.apply {
        layoutManager = LinearLayoutManager(this@MainActivity)
        setHasFixedSize(true)
    }

    private fun showDataNullDialog(): AlertDialog = alertDialog
        .setIcon(R.drawable.ic_dangerous)
        .setTitle(R.string.NullDialog_Title)
        .setMessage(R.string.NullDialog_Content)
        .setPositiveButton(R.string.NullDialog_okBtn, null)
        .show()

}