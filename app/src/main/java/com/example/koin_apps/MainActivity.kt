package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.data.recyclerViewAdapter.MainRecyclerAdapter
import com.example.koin_apps.viewModel.activity.MainViewModel
import com.example.koin_apps.databinding.ActivityMainBinding
import com.example.koin_apps.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var vmFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        mainViewModel = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        mainActivityBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)

        setContentView(mainActivityBinding.root)
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.readAllCoinData.observe(this, {
            if(it.isNullOrEmpty()) { showDataNullDialog() }
            else {
                mainActivityBinding.mainRecyclerView.adapter = MainRecyclerAdapter(it, mainViewModel)
                mainViewModel.getTickerPrice(it)
            }
        })

        mainActivityBinding.addCoinBtn.setOnClickListener {
            startActivity(Intent(this, SelectKoinActivity::class.java))
            finish()
        }
    }

    private fun showDataNullDialog() {
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle("Cryptocurrency wasn't chosen")
            .setMessage("Cryptocurrency wasn't chosen \nPlz Choose your favorite Cryptocurrency")
            .setIcon(R.drawable.ic_dangerous)
            .setPositiveButton("OK") {_, _ ->}
            .create()
            .show()
    }

}