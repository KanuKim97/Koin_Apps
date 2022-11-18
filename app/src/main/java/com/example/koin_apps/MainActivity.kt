package com.example.koin_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.data.recyclerViewAdapter.MainRecyclerAdapter
import com.example.koin_apps.viewModel.activity.MainViewModel
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetroRepo
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import com.example.koin_apps.databinding.ActivityMainBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
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

        //Todo("Observe ViewModel Livedata")

        mainViewModel.readAllCoinData.observe(this, {
            mainActivityBinding.mainRecyclerView.adapter = MainRecyclerAdapter(it, mainViewModel)
        })

        mainActivityBinding.tradePageBtn.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tradePageBtn -> {

            }
        }
    }

}