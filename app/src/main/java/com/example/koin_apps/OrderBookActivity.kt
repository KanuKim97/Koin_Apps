package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.RoomRepo
import com.example.koin_apps.data.remote.IKoinApiService
import com.example.koin_apps.data.remote.RetroRepo
import com.example.koin_apps.data.remote.model.orderBook.OrderRoot
import com.example.koin_apps.databinding.ActivityOrderBookBinding
import com.example.koin_apps.viewModel.ViewModelFactory
import com.example.koin_apps.viewModel.activity.OrderBookViewModel
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderBookActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var orderBookBinding: ActivityOrderBookBinding
    private lateinit var vmFactory: ViewModelFactory
    private lateinit var orderBookViewModel: OrderBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vmFactory = ViewModelFactory(AppRepository(RoomRepo.provideDao(RoomRepo.createAppDBClient())))
        orderBookViewModel = ViewModelProvider(this, vmFactory)[OrderBookViewModel::class.java]
        orderBookBinding = ActivityOrderBookBinding.inflate(layoutInflater)
        setContentView(orderBookBinding.root)
    }

    override fun onResume() {
        super.onResume()
        /*
            Todo: Observe ViewModel Livedata
        */
        orderBookBinding.activeBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val coinTitle = intent.getStringExtra("CoinTitle").toString()

        when(v?.id) {
            R.id.activeBtn ->
                orderBookViewModel.getOrderData(coinTitle, 5)
        }

    }
}