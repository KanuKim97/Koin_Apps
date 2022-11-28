package com.example.koin_apps.data.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koin_apps.R
import com.example.koin_apps.data.remote.model.mainViewTicker.MainTickerData
import com.example.koin_apps.databinding.MainCoinviewItemBinding


class MainRecyclerAdapter(
    private var coinTitleList: MainTickerData
): RecyclerView.Adapter<MainRecyclerAdapter.MainViewTitleHolder>(){

    class MainViewTitleHolder(binding: MainCoinviewItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            val titleCoin = binding.titleCoin
            //val titlePrice = binding.tickerPrice
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
        val coinDesc = coinTitleList
        holder.titleCoin.text = coinDesc.coinTitle
        //holder.titlePrice.text =
    }


    //override fun getItemCount(): Int = coinTitleList.size

    override fun getItemViewType(position: Int): Int = position

    override fun getItemCount(): Int { TODO("Not yet implemented") }

}