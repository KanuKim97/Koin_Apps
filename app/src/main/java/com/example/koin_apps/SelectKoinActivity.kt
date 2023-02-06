package com.example.koin_apps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin_apps.databinding.ActivitySelectKoinBinding
import com.example.koin_apps.viewModel.activity.SelectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectKoinActivity : AppCompatActivity(){
    private lateinit var selectKoinBinding: ActivitySelectKoinBinding
    private lateinit var selectAdapter: SelectRecyclerAdapter
    private val selectViewModel: SelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectKoinBinding = ActivitySelectKoinBinding.inflate(layoutInflater)
        selectKoinBinding.CoinRecyclerView.layoutManager = LinearLayoutManager(this)

        setContentView(selectKoinBinding.root)
    }

    override fun onResume() {
        super.onResume()
        selectViewModel.getCoinTitle()

        selectViewModel.coinTitleList.observe(this) {
            selectKoinBinding.CoinRecyclerView.adapter = SelectRecyclerAdapter(it)
        }

        selectViewModel.selectedTitleCoin.observe(this) {
            if (it.size == 0) { selectKoinBinding.ListSelected.visibility = View.GONE }
            else { selectKoinBinding.ListSelected.text = it.toString() }
        }

        selectKoinBinding.compSelectBtn.setOnClickListener {
            // ViewModel -> Room-Database Store selected Coin Title data
            // selectViewModel.storeCoinTitleData
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}