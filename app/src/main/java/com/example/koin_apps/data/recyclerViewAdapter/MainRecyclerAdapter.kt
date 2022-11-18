package com.example.koin_apps.data.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.R
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.databinding.MainCoinviewItemBinding
import com.example.koin_apps.viewModel.activity.MainViewModel

class MainRecyclerAdapter(
    private var coinTitleList: List<CoinEntity>,
    private var mainViewModel: MainViewModel
): RecyclerView.Adapter<MainRecyclerAdapter.MainViewTitleHolder>(){

    class MainViewTitleHolder(binding: MainCoinviewItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            val titleCoin = binding.titleCoin
            val titlePrice = binding.tickerPrice
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewTitleHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.main_coinview_item, parent, false)

        return MainViewTitleHolder(MainCoinviewItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: MainViewTitleHolder, position: Int) {
        val coinDesc = coinTitleList[position]
        holder.titleCoin.text = coinDesc.coinTitle
    }

    override fun getItemCount(): Int = coinTitleList.size

    override fun getItemViewType(position: Int): Int = position

}