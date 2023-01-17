package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.data.recyclerViewAdapter.MainRecyclerAdapter
import com.example.koin_apps.data.remote.model.ticker.mainViewTicker.MainTickerData
import com.example.koin_apps.viewModel.activity.MainViewModel
import com.example.koin_apps.databinding.ActivityMainBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var vmFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private val MainAdapter by lazy { MainRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        mainViewModel = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        mainActivityBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainActivityBinding.mainRecyclerView.adapter = MainAdapter

        setContentView(mainActivityBinding.root)
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.readAllCoinData.observe(this, {
            if(it.isNullOrEmpty()) { showDataNullDialog() }
            else { mainViewModel.getPriceTicker(coinEntity = it) }
        })

        mainViewModel.tickerLiveData.observe(this, {
            MainAdapter.setData(it)
        })

        mainActivityBinding.addCoinBtn.setOnClickListener {
            startActivity(Intent(this, SelectKoinActivity::class.java))
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart()", "onRestart()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause()", "onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestory()", "OnDestory()")
    }

    private fun showDataNullDialog() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(getString(R.string.NullDialog_Title))
            .setMessage(getString(R.string.NullDialog_Content))
            .setIcon(R.drawable.ic_dangerous)
            .setPositiveButton(getString(R.string.NullDialog_okBtn)) {_, _ ->}
            .create()
            .show()
    }

}